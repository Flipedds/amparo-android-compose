package com.table.tatu.amparo.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.table.tatu.amparo.ui.screens.CadastroScreen
import com.table.tatu.amparo.ui.screens.HomeScreen
import com.table.tatu.amparo.ui.screens.InitialScreen
import com.table.tatu.amparo.ui.screens.LoginScreen
import com.table.tatu.amparo.ui.states.CadastroFormState
import com.table.tatu.amparo.ui.viewmodels.CadastroScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.LoginScreenViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

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
    val loginScreenViewModel = koinViewModel<LoginScreenViewModel>()
    val cadastroScreenViewModel = koinViewModel<CadastroScreenViewModel>()
    val isLoggedIn by loginScreenViewModel.isLoggedIn.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(loginScreenViewModel.toastEvent) {
        loginScreenViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    NavHost(navController = navController, startDestination = if (isLoggedIn) Home else Initial) {
        composable<Initial> {
            InitialScreen(onNavigateToLogin = {
                navController.navigate(Login) {
                    popUpTo<Initial> {
                        inclusive = true
                    }
                }
            })
        }
        composable<Login> {
            LoginScreen(
                loginScreenViewModel = loginScreenViewModel,
                onNavigateToHome = { login: String, senha: String ->
                    loginScreenViewModel.authenticateUser(login, senha,
                        onSuccess = {
                            navController.navigate(Home) {
                                popUpTo<Login> {
                                    inclusive = true
                                }
                            }
                        })
                },
                onNavigateToCadastro = {
                    navController.navigate(Cadastro)
                })
        }
        composable<Cadastro> {
            CadastroScreen(
                cadastroScreenViewModel = cadastroScreenViewModel,
                onNavigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo<Cadastro> {
                            inclusive = true
                        }
                    }
                }, onCreateNewUser = { form: CadastroFormState ->
                    cadastroScreenViewModel.createNewUser(
                        form,
                        onSuccess = {
                            navController.navigate(Login) {
                                popUpTo<Cadastro> {
                                    inclusive = true
                                }
                            }
                        }
                    )
                })
        }
        composable<Home> {
            HomeScreen(onLogout = {
                loginScreenViewModel.setLoggedIn(false)
                navController.navigate(Login) {
                    popUpTo<Home> {
                        inclusive = true
                    }
                }
            })
        }
    }
}