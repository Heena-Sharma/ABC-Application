package com.abcapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abcapp.R
import com.abcapp.domain.Project
import com.abcapp.ui.theme.Pink10

/**
 * Compose view for the list item
 * */
@Composable
fun ListViewUI(item: Project) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen._10sdp)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen._10sdp)),

    ) {
        Row(
            modifier = Modifier
               .background(Pink10),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.mainImageURL,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier .padding(dimensionResource(id = R.dimen._8sdp))
                    .size(dimensionResource(id = R.dimen._60sdp))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen._10sdp)))
            )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen._10sdp))
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen._16sdp).value.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._3sdp)))
                Text(
                    text = item.shortDescription,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen._14sdp).value.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}
