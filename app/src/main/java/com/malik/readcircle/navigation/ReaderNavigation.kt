package com.malik.readcircle.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.malik.readcircle.screens.ReaderSplashScreen
import com.malik.readcircle.screens.home.ReaderHomeScreen
import com.malik.readcircle.screens.login.LoginScreen
import com.malik.readcircle.screens.search.BookSearchScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderNavigation(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name){

        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController=navController)
        }

        composable(ReaderScreens.LoginScreen.name){
            LoginScreen(navController=navController)
        }

        composable(ReaderScreens.SearchScreen.name){
            BookSearchScreen(navController=navController)
        }
    }
}