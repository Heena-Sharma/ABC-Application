package com.abcapp.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.abcapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsBottomSheetDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(topStart = dimensionResource(id = R.dimen._16sdp), topEnd = dimensionResource(id = R.dimen._16sdp)),
        containerColor = MaterialTheme.colorScheme.surface,
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen._16sdp))
            ) {
                content()
            }
        }
    )
}
