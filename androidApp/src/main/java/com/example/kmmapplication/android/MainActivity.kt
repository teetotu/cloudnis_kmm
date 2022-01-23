package com.example.kmmapplication.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.kmmapplication.android.ui.CountryInfoCardsList
import com.example.kmmapplication.entity.CountryInfoCard
import com.example.kmmapplication.network.CountryInfoCardApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    private val api = CountryInfoCardApi()
    private lateinit var countryInfoCards: List<CountryInfoCard>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Country info cards"

        displayCountryInfoCards()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun displayCountryInfoCards() {
        mainScope.launch {
            kotlin.runCatching {
                api.getAllCountryInfoCards()
            }.onSuccess {
                setContent {
                    Surface(color = MaterialTheme.colors.background) {
                        var refreshing by remember { mutableStateOf(false) }
                        LaunchedEffect(refreshing) {
                            if (refreshing) {
                                delay(3000)
                                refreshing = false
                            }
                        }
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing = refreshing),
                            onRefresh = { refreshing = true },
                        ) {
                            CountryInfoCardsList(
                                modifier = Modifier.fillMaxSize(),
                                cards = it
                            )
                        }
                    }
                }
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
