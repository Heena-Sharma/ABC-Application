package com.abcapp.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.abcapp.R
import com.abcapp.data.FundingProject
import com.abcapp.ui.compose.ListViewUI
import com.abcapp.ui.theme.Pink20
import com.abcapp.ui.theme.Pink40
import com.abcapp.ui.theme.Pink80
import com.abcapp.ui.theme.PurpleGrey40
import com.abcapp.ui.theme.PurpleGrey80
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselView(pagerState: PagerState, carouselListData: List<FundingProject>) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(dimensionResource(id = R.dimen._12sdp))
            .background(Color.White)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen._200sdp))
        ) { page ->
            val project = carouselListData.getOrNull(page)
            project?.mainImageURL?.let { imageUrl ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = dimensionResource(id = R.dimen._2sdp)
                    )
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._200sdp))
                            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._10sdp)))
                    )
                }
            }
        }

        CarouselIndicators(pagerState.pageCount, pagerState.currentPage)
    }
}

@Composable
fun CarouselIndicators(pageCount: Int, currentPage: Int) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen._10sdp)),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPage == iteration) Pink80 else Pink40
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen._2sdp))
                    .clip(CircleShape)
                    .background(color)
                    .size(dimensionResource(id = R.dimen._8sdp))
            )
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchText,
        placeholder = { Text(stringResource(R.string.search), color = PurpleGrey80) },
        singleLine = true,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen._20sdp)),
        leadingIcon = {
            Icon(Icons.Filled.Search, "", tint = PurpleGrey40)
        },
        onValueChange = {
            searchText = it
            onSearch(it)
        },
        textStyle = TextStyle(color = Color.Black),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Pink20,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Pink20,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen._10sdp))
    )
}
