package com.table.tatu.amparo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.table.tatu.amparo.ui.components.CadastroForm
import com.table.tatu.amparo.ui.states.CadastroFormState
import org.junit.Rule
import org.junit.Test

class CadastroFormTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayNomeCompletoFieldAndLabel() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }
        composeTestRule.onNodeWithText("Nome Completo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("nome field").assertIsEnabled()
    }

    @Test
    fun shouldDisplayCpfFieldAndLabel() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }
        composeTestRule.onNodeWithText("Cpf").assertIsDisplayed()
        composeTestRule.onNodeWithTag("cpf field").assertIsEnabled()
    }

    @Test
    fun shouldDisplayEmailFieldAndLabel() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }
        composeTestRule.onNodeWithText("E-mail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("email field").assertIsEnabled()
    }

    @Test
    fun shouldDisplaySenhaFieldAndLabel() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }
        composeTestRule.onNodeWithText("Senha").assertIsDisplayed()
        composeTestRule.onNodeWithTag("senha field").assertIsEnabled()
    }

    @Test
    fun shouldDisplayConfirmarSenhaFieldAndLabel() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }
        composeTestRule.onNodeWithText("Confirmar Senha").assertIsDisplayed()
        composeTestRule.onNodeWithTag("confirmar senha field").assertIsEnabled()
    }

    @Test
    fun shouldDisplaySenhaInvalidaOnWeakPass() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }

        composeTestRule.onNodeWithTag("senha field").performTextInput("12345")
        composeTestRule.onNodeWithTag("confirmar senha field").performTextInput("12345")
        composeTestRule.onNodeWithText("Senha inválida").assertIsDisplayed()
    }

    @Test
    fun shouldDisplaySenhasDevemSerIguaisOnDiffPass() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }

        composeTestRule.onNodeWithTag("senha field").performTextInput("12345")
        composeTestRule.onNodeWithTag("confirmar senha field").performTextInput("1234")
        composeTestRule.onNodeWithText("Senhas devem ser iguais").assertIsDisplayed()
    }


    @Test
    fun shouldNotEnableEntrarButtonOnNotFilledForm() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }

        composeTestRule.onNodeWithTag("enviar button").assertIsNotEnabled()
    }

    @Test
    fun shouldEnableEntrarButtonOnFullFilledForm() {
        composeTestRule.setContent {
            CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
        }

        composeTestRule.onNodeWithTag("nome field").performTextInput("maria")
        composeTestRule.onNodeWithTag("cpf field").performTextInput("49348348394")
        composeTestRule.onNodeWithTag("email field").performTextInput("maria@gmail.com")
        composeTestRule.onNodeWithTag("senha field").performTextInput("s8@S1U93")
        composeTestRule.onNodeWithTag("confirmar senha field").performTextInput("s8@S1U93")
        composeTestRule.onNodeWithTag("enviar button").assertIsEnabled()
    }
}