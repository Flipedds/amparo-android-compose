package com.table.tatu.amparo.ui.states

data class CadastroFormState(
    var nome: String = "",
    var cpf: String = "",
    var email: String = "",
    var senha: String = "",
    var confirmarSenha: String = ""
)
