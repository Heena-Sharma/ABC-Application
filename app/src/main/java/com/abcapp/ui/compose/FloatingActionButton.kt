package com.abcapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.abcapp.R
import com.abcapp.ui.theme.Pink1000
import com.abcapp.ui.theme.Purple40

@Composable
fun FloatingActionButton(click: () -> Unit) {
    FloatingActionButton(
        onClick = click, containerColor = Pink1000,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen._2sdp))
            .clip(CircleShape)
            .background(Pink1000)
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            tint = Purple40,
            contentDescription = "Statistics",
            )
    }
}
