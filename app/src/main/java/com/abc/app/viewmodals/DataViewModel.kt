package com.abc.app.viewmodals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abc.app.data.Stats
import com.abc.app.domain.FundingProject
import com.abc.app.domain.FundingProjectRepository
import com.abc.app.domain.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val recordRepository: FundingProjectRepository,
) : ViewModel() {

    private val _carousalList = MutableStateFlow<List<FundingProject>>(emptyList())
    val carousalList: StateFlow<List<FundingProject>> = _carousalList.asStateFlow()

    private val _filteredListData = MutableStateFlow<List<Project>>(emptyList())
    val filteredListData: StateFlow<List<Project>> = _filteredListData.asStateFlow()

    private var currentPosition = 0

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        recordRepository.getFundingProjects().collect { data ->
            _carousalList.value = data
            if (data.isNotEmpty()) {
                _filteredListData.value = data[0].list
                currentPosition = 0
            }
        }
    }

    fun getData(position: Int) = viewModelScope.launch {
        currentPosition = position
        val data = carousalList.value
        if (data.isNotEmpty() && position in data.indices) {
            _filteredListData.value = data[position].list
        }
    }

    fun filterListData(searchText: String) = viewModelScope.launch {
        val data = carousalList.value
        _filteredListData.value = if (searchText.isNotEmpty()) {
            data[currentPosition].list.filter { project ->
                project.title.contains(searchText, ignoreCase = true)
            }
        } else {
            data[currentPosition].list
        }
    }

    fun getStats(projects: List<Project>): Deferred<Stats> =
        CoroutineScope(Dispatchers.Default).async {
            val pageCount = projects.size
            val charCounts = projects.flatMap { project ->
                (project.title + project.shortDescription).lowercase().toList()
            }
                .filter { it.isLetter() }
                .groupBy { it }
                .mapValues { it.value.size }

            val top3CharsWithCounts = charCounts.entries.sortedByDescending { it.value }.take(3)
            Stats(pageCount, top3CharsWithCounts.map { it.key }, top3CharsWithCounts)
        }
}