package com.abcapp.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.abcapp.R
import com.abcapp.ui.theme.Pink20
import com.abcapp.ui.theme.PurpleGrey40
import com.abcapp.ui.theme.PurpleGrey80

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