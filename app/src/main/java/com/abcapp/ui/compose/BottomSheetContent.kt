package com.abcapp.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcapp.viewmodal.DataViewModel

@Composable
fun BottomSheetContent(
    showBottomSheet: MutableState<Boolean>,
    viewModel: DataViewModel = hiltViewModel()
) {
    MainScreen(viewModel)
    if (showBottomSheet.value) {
        StatsBottomSheetDialog(
            onDismissRequest = { showBottomSheet.value = false }
        ) {
            Stats(viewModel)
        }
    }
}
