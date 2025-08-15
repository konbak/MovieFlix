package app.example.home

fun HomeState.reduce(result: HomeResult): HomeState =
    when (result) {
        is HomeResult.FavoritesUpdated -> copy(favoriteIds = result.ids)
    }