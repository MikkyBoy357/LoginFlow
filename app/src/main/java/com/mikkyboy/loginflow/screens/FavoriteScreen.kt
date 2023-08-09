package com.mikkyboy.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikkyboy.loginflow.components.FavAppBar
import com.mikkyboy.loginflow.components.ItemCard
import com.mikkyboy.loginflow.data.favorite.FavoriteUIEvent
import com.mikkyboy.loginflow.data.favorite.FavoriteViewModel
import com.mikkyboy.loginflow.navigation.PostOfficeAppRouter
import com.mikkyboy.loginflow.navigation.Screen
import com.mikkyboy.loginflow.navigation.SystemBackButtonHandler
import kotlinx.coroutines.delay

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()


    Scaffold(scaffoldState = scaffoldState, topBar = {
        FavAppBar(
            title = "Favourites",
            navigationIconClicked = {
                favoriteViewModel.toHomeScreen()
            },
        )
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                if (favoriteViewModel.favoriteUIState.value.favoriteCryptos.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.padding()) {
                        items(favoriteViewModel.favoriteUIState.value.favoriteCryptos) {
                            ItemCard(cryptoName = it)
                        }
                    }
                } else {
                    Text(text = "Nothing to show here")
                }
                Button(onClick = {
                    favoriteViewModel.getFavoriteCryptos() {
                        favoriteViewModel.onEvent(
                            FavoriteUIEvent.FavoriteCryptosFetched(it)
                        )
                    }
                }) {
                    Text(text = "Load")
                }
            }

        }

    }

    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
    }
}