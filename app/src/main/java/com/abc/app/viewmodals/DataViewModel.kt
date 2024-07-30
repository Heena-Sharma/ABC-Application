package com.abc.app.viewmodals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abc.app.data.DataRepository
import com.abc.app.data.FundingProject
import com.abc.app.data.Project
import com.abc.app.data.Stats
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
    private val recordRepository: DataRepository,
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
        recordRepository.getData().collect { data ->
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
            val charCounts = mutableMapOf<Char, Int>()

            for (project in projects) {
                for (char in project.title.lowercase()) {
                    if (char.isLetter()) charCounts[char] = charCounts.getOrDefault(char, 0) + 1
                }
                for (char in project.shortDescription.lowercase()) {
                    if (char.isLetter()) charCounts[char] = charCounts.getOrDefault(char, 0) + 1
                }
            }

            val top3CharsWithCounts = charCounts.entries.sortedByDescending { it.value }.take(3)
            Stats(pageCount, top3CharsWithCounts.map { it.key }, top3CharsWithCounts)
        }
}