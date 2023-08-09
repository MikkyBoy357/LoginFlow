package com.mikkyboy.loginflow.app

import androidx.compose.animation.Crossfade
import androidx.compose.material.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikkyboy.loginflow.data.home.HomeViewModel
import com.mikkyboy.loginflow.navigation.PostOfficeAppRouter
import com.mikkyboy.loginflow.navigation.Screen
import com.mikkyboy.loginflow.screens.FavoriteScreen
import com.mikkyboy.loginflow.screens.LoginScreen
import com.mikkyboy.loginflow.screens.SignUpScreen
import com.mikkyboy.loginflow.screens.TermsAndConditionsScreen
import com.mikkyboy.loginflow.screens.HomeScreen

@Composable
fun PostOfficeApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        if (homeViewModel.isUserLoggedIn.value == true) {
            PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = PostOfficeAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }

                is Screen.FavoriteScreen -> {
                    FavoriteScreen()
                }
            }

        }
    }
}