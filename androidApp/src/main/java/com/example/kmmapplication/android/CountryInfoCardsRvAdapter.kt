package com.example.kmmapplication.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmapplication.entity.CountryInfoCard

class CountryInfoCardsRvAdapter(var countryInfoCards: List<CountryInfoCard>) :
    RecyclerView.Adapter<CountryInfoCardsRvAdapter.CountryInfoCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryInfoCardViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country_info_card, parent, false)
            .run(::CountryInfoCardViewHolder)
    }

    override fun getItemCount(): Int = countryInfoCards.count()

    override fun onBindViewHolder(holder: CountryInfoCardViewHolder, position: Int) {
        holder.bindData(countryInfoCards[position])
    }

    inner class CountryInfoCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryNameTextView = itemView.findViewById<TextView>(R.id.name)
        private val countryCapitalTextView = itemView.findViewById<TextView>(R.id.capital)
        private val countryRegionTextView = itemView.findViewById<TextView>(R.id.region)
        private val countryPopulationTextView = itemView.findViewById<TextView>(R.id.population)

        fun bindData(countryInfoCard: CountryInfoCard) {
            val ctx = itemView.context
            countryNameTextView.text = ctx.getString(R.string.name_field, countryInfoCard.name)
            countryCapitalTextView.text =
                ctx.getString(R.string.capital_filed, countryInfoCard.capital)
            countryRegionTextView.text =
                ctx.getString(R.string.region_field, countryInfoCard.region)
            countryPopulationTextView.text =
                ctx.getString(R.string.population, countryInfoCard.population.toString())
        }
    }
} 