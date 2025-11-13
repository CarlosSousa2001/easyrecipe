package com.crs.receitafacil.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.crs.receitafacil.ui.presentation.navigation.RootHost
import com.crs.receitafacil.ui.presentation.navigation.screens.Graphs
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            ReceitaFacilTheme {

                val navController: NavHostController = rememberNavController()

                RootHost(
                    startDestination = Graphs.AuthGraph,
                    navController = navController
                )
            }
        }
    }
}
