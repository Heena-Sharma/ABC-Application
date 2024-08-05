package com.abcapp.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.abcapp.R
import com.abcapp.domain.FundingProject

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