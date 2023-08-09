package com.mikkyboy.loginflow.data.favorite

sealed class FavoriteUIEvent {

    data class FavoriteCryptosFetched(val favoriteCryptos: List<String>) : FavoriteUIEvent()

}
