package  com.abcapp.viewmodal
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcapp.domain.FundingProject
import com.abcapp.domain.Project
import com.abcapp.data.Stats
import com.abcapp.domain.FundingProjectRepository
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
    private val recordRepository: FundingProjectRepository
) : ViewModel() {

    private val _carouselList = MutableStateFlow<List<FundingProject>>(emptyList())
    val carouselList: StateFlow<List<FundingProject>> = _carouselList.asStateFlow()
    private var currentPosition = 0
    private val _filteredListData = MutableStateFlow<List<Project>>(emptyList())
    val filteredListData: StateFlow<List<Project>> = _filteredListData.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            recordRepository.getFundingProjects().collect { data ->
                _carouselList.value = data
                _filteredListData.value = data.firstOrNull()?.list ?: emptyList()
            }
        }
    }

    fun filterListData(searchText: String) = viewModelScope.launch {
        val data = carouselList.value
        _filteredListData.value = if (searchText.isNotEmpty()) {
            data[currentPosition].list.filter { project ->
                project.title.contains(searchText, ignoreCase = true)
            }
        } else {
            data[currentPosition].list
        }
    }
    fun setSelection(position:  Int) {
        currentPosition= position
        viewModelScope.launch {
            _filteredListData.value = carouselList.value.get(position).list
        }
    }
    fun getStats(): Deferred<Stats> =
        CoroutineScope(Dispatchers.Default).async {
            val pageCount = _filteredListData.value.size
            val charCounts = _filteredListData.value.flatMap { project ->
                (project.title + project.shortDescription).lowercase().toList()
            }
                .filter { it.isLetter() }
                .groupBy { it }
                .mapValues { it.value.size }

            val top3CharsWithCounts = charCounts.entries.sortedByDescending { it.value }.take(3)
            Stats(
                pageCount,
                top3CharsWithCounts.map { it.key },
                top3CharsWithCounts
            )
        }
}