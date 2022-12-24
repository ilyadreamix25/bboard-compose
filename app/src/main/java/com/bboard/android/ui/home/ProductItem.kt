package com.bboard.android.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bboard.android.data.dto.Product
import com.bboard.android.ui.theme.RatingBad
import com.bboard.android.ui.theme.RatingGood
import kotlin.math.roundToInt

@Composable
fun ProductItem(product: Product) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.border(
            border = BorderStroke(1.dp, Color.Black.copy(.2f)),
            shape = RoundedCornerShape(12.dp),
        ),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black.copy(.2f))
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = product.rating.roundToInt().toString(),
                        color = Color.Black.copy(.5f),
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    repeat(product.rating.roundToInt()) {
                        Icon(
                            Icons.Rounded.Star,
                            null,
                            tint = RatingGood
                        )
                    }
                    repeat(5 - product.rating.roundToInt()) {
                        Icon(
                            Icons.Rounded.Star,
                            null,
                            tint = RatingBad
                        )
                    }
                }

                Text(
                    text = "$ ${product.price}",
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                Text(
                    text = "$ ${(product.price - product.price * (product.discountPercentage / 100)).roundToInt()}",
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = product.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}