package com.example.demo_androidx_fragment_compose_repro

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.compose.AndroidFragment
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val view = LocalView.current
    LaunchedEffect(navController) {
        Navigation.setViewNavController(view, navController)
    }

    NavHost(navController, startDestination = "main", modifier = modifier) {
        composable("main") { AndroidFragment<MainFragment>() }
        composable("detail") { AndroidFragment<DetailFragment>() }
    }
}
