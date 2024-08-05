package com.abcapp.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcapp.viewmodal.DataViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(dataViewModel: DataViewModel = hiltViewModel()) {
    val filteredListItems by dataViewModel.filteredListData.collectAsState()
    val carouselListData by dataViewModel.carouselList.collectAsState()
    val pagerState = rememberPagerState(pageCount = { carouselListData.size })
    var showCarousel by rememberSaveable { mutableStateOf(true) }
    val scrollState = rememberLazyListState()

    LaunchedEffect(scrollState.firstVisibleItemIndex) {
        showCarousel = scrollState.firstVisibleItemIndex == 0
    }

    LaunchedEffect(pagerState.currentPage) {
        dataViewModel.setSelection(pagerState.currentPage)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = showCarousel) {
            CarouselView(pagerState, carouselListData)
        }

        SearchBar(onSearch = { query ->
            dataViewModel.filterListData(query)
        })

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = scrollState
        ) {
            items(filteredListItems) { item ->
                ListViewUI(item)
            }
        }
    }
}