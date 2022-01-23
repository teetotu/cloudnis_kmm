package com.example.kmmapplication.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmmapplication.android.ui.theme.KMM_ApplicationTheme
import com.example.kmmapplication.entity.CountryInfoCard

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val mock =
        CountryInfoCard(
            "Russia",
            "Moscow",
            "Europe",
            144104080
        )
    KMM_ApplicationTheme {
        CountryInfoCardItem(mock)
    }
}

@Composable
fun CountryInfoCardItem(card: CountryInfoCard) {
    Column {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Country: " + card.name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                ),
            )
        };
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Capital: " + card.capital,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                ),
            )
        };
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Region: " + card.region,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                ),
            );
        };
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Population: " + card.population.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                ),
            )
        }
    };
}