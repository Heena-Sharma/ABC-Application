package com.abcapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.abcapp.R
import com.abcapp.ui.theme.Pink40
import com.abcapp.ui.theme.Pink80

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