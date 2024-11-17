package com.table.tatu.amparo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.table.tatu.amparo.ui.screens.CadastroScreen
import com.table.tatu.amparo.ui.screens.HomeScreen
import com.table.tatu.amparo.ui.screens.InitialScreen
import com.table.tatu.amparo.ui.screens.LoginScreen
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
                navController.navigate(Login){
                    popUpTo<Initial>{
                        inclusive = true
                    }
                }
            })
        }
        composable<Login> {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Home){
                        popUpTo<Login>{
                            inclusive = true
                        }
                    }
                },
                onNavigateToCadastro = {
                    navController.navigate(Cadastro)
                })
        }
        composable<Cadastro> {
            CadastroScreen(onNavigateToLogin = {
                navController.navigate(Login){
                    popUpTo<Cadastro>()
                }
            })
        }
        composable<Home> {
            HomeScreen()
        }
    }
}