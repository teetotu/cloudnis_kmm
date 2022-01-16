//
//  CountryInfoRow.swift
//  iosApp
//
//  Created by Akhmad Shair Kh Dilavar on 16.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CountryInfoCardRow: View {
    var card: CountryInfoCard

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text("Country: \(card.name)")
                Text("Capital: \(String(card.capital))")
                Text("Region: \(String(card.region))")
                Text("Population: \(String(card.population))")
            }
            Spacer()
        }
    }
}
