package com.table.tatu.amparo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.table.tatu.amparo.samples.sampleEventos
import com.table.tatu.amparo.screens.CadastroScreen
import com.table.tatu.amparo.screens.HomeScreen
import com.table.tatu.amparo.screens.InitialScreen
import com.table.tatu.amparo.screens.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Initial

@Serializable
object Login

@Serializable
object Cadastro

@Serializable
object Home

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Initial) {
        composable<Initial> {
            InitialScreen(onNavigateToLogin = {
                navController.navigate(Login)
            })
        }
        composable<Login> {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Home)
                },
                onNavigateToCadastro = {
                    navController.navigate(Cadastro)
                })
        }
        composable<Cadastro> {
            CadastroScreen(onNavigateToLogin = {
                navController.navigate(Login)
            })
        }
        composable<Home> {
            HomeScreen(navController = navController, sampleEventos)
        }
    }
}