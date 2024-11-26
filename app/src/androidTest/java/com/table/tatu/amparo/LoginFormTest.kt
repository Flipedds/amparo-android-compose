package com.table.tatu.amparo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.table.tatu.amparo.ui.components.LoginForm
import org.junit.Rule
import org.junit.Test

class LoginFormTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayEmailOrTelephoneFieldAndLabel(){
        composeTestRule.setContent {
            LoginForm(
                onNavigateToHome = { _, _ ->},
                onNavigateToCadastro = {}
            )
        }
        composeTestRule.onNodeWithText("Telefone ou E-mail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("email field").assertIsEnabled()
    }

    @Test
    fun shouldDisplayPasswordFieldAndLabel(){
        composeTestRule.setContent {
            LoginForm(
                onNavigateToHome = { _, _ ->},
                onNavigateToCadastro = {}
            )
        }

        composeTestRule.onNodeWithText("Senha").assertIsDisplayed()
        composeTestRule.onNodeWithTag("senha field").assertIsEnabled()
    }

    @Test
    fun shouldNotEnableEntrarButtonOnNotFilledForm(){
        composeTestRule.setContent {
            LoginForm(
                onNavigateToHome = { _, _ ->},
                onNavigateToCadastro = {}
            )
        }

        composeTestRule.onNodeWithTag("entrar button").assertIsNotEnabled()
    }

    @Test
    fun shouldEnableEntrarButtonOnFullFilledForm(){
        composeTestRule.setContent {
            LoginForm(
                onNavigateToHome = { _, _ ->},
                onNavigateToCadastro = {}
            )
        }

        composeTestRule.onNodeWithTag("email field").performTextInput("teste")
        composeTestRule.onNodeWithTag("senha field").performTextInput("12345")
        composeTestRule.onNodeWithTag("entrar button").assertIsEnabled()
    }
}