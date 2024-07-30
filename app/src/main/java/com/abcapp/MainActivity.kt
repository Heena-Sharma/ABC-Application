package com.abcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.abcapp.ui.compose.BottomSheetContent
import com.abcapp.ui.compose.CustomTopAppBar
import com.abcapp.ui.compose.FloatingActionButton
import com.abcapp.ui.theme.ABCAppTheme
import com.abcapp.viewmodal.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ABCAppTheme {
                val viewModel by viewModels<DataViewModel>()
                val showBottomSheet = rememberSaveable { mutableStateOf(false) }
                Scaffold(topBar = { CustomTopAppBar(title =getString(R.string.app_name)) },
                    containerColor = Color.White,
                    floatingActionButton = {
                        FloatingActionButton { showBottomSheet.value = true }
                    }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        BottomSheetContent(showBottomSheet, viewModel)
                    }
                }
            }
        }
    }
}