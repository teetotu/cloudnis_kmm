import SwiftUI
import shared

struct ContentView: View {
  @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("Country info cards")
            .navigationBarItems(trailing:
                Button("Reload") {
                    self.viewModel.loadCountryInfoCards(forceReload: true)
            })
        }
    }

    private func listView() -> AnyView {
        switch viewModel.countryInfoCards {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let countryInfoCards):
            return AnyView(List(countryInfoCards) { countryInfoCard in
                CountryInfoCardRow(card: countryInfoCard)
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}

extension ContentView {
    enum LoadableCountryInfoCards {
        case loading
        case result([CountryInfoCard])
        case error(String)
    }
    
    class ViewModel: ObservableObject {
            let sdk: CountriesInfoSDK
            @Published var countryInfoCards = LoadableCountryInfoCards.loading

            init(sdk: CountriesInfoSDK) {
                self.sdk = sdk
                self.loadCountryInfoCards(forceReload: false)
            }

            func loadCountryInfoCards(forceReload: Bool) {
                self.countryInfoCards = .loading
                sdk.getcountryInfoCards(forceReload: forceReload, completionHandler: { countryInfoCards, error in
                    if let countryInfoCards = countryInfoCards {
                        self.countryInfoCards = .result(countryInfoCards)
                    } else {
                        self.countryInfoCards = .error(error?.localizedDescription ?? "error")
                    }
                })
            }
        }
}

extension CountryInfoCard: Identifiable { }
