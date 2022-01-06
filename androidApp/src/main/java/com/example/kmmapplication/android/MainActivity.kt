package com.example.kmmapplication.android

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kmmapplication.CountriesInfoSDK
import com.example.kmmapplication.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    private lateinit var countryInfoCardsRecyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val sdk = CountriesInfoSDK(DatabaseDriverFactory(this))

    private val countryInfoCardsRvAdapter = CountryInfoCardsRvAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Country info cards"
        setContentView(R.layout.activity_main)

        countryInfoCardsRecyclerView = findViewById(R.id.countryInfoCardsRvAdapter)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        countryInfoCardsRecyclerView.adapter = countryInfoCardsRvAdapter
        countryInfoCardsRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            displayCountryInfoCards(true)
        }

        displayCountryInfoCards(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun displayCountryInfoCards(needReload: Boolean) {
        progressBarView.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                sdk.getcountryInfoCards(needReload)
            }.onSuccess {
                countryInfoCardsRvAdapter.countryInfoCards = it
                countryInfoCardsRvAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            progressBarView.isVisible = false
        }
    }
}
