package com.haiphong.algorithmsvisualizer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haiphong.algorithmsvisualizer.ui.screens.MainScreen
import com.haiphong.algorithmsvisualizer.ui.screens.TestAnimationScreen

enum class Route {
    Main,
    TestAnimation
}

@Composable
fun AlgorithmVisualizerApp(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Route.Main.name ) {
        composable(route = Route.Main.name) {
            MainScreen()
        }
        composable(route = Route.TestAnimation.name) {
            TestAnimationScreen()
        }
    }
}