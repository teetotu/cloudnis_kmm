package com.example.kmmapplication.android.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmmapplication.entity.CountryInfoCard

@Composable
fun CountryInfoCardsList(
    modifier: Modifier = Modifier,
    cards: List<CountryInfoCard>
) {
    LazyColumn(modifier = modifier) {
        items(cards) { card ->
            CountryInfoCardItem(card)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}