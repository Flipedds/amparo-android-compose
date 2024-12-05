package com.table.tatu.amparo.extensions

import com.table.tatu.amparo.enums.Relation

fun String.isEqualsAndNotBlankAndValid(string: String): Boolean {
    return this == string && this.isNotBlank() && this.isPassValid()
}

fun String.isDifferent(string: String) : Boolean {
    return string != this
}

fun String.isPassValid(): Boolean {
    if (this.isEmpty()) return true
    return this.matches(Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"))
}

fun String.isEmailValid(): Boolean {
    if (this.isEmpty()) return true
    return this.matches(Regex("[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))
}

fun String.isCPFValid(): Boolean {
    if (this.isEmpty()) return true
    val cpf = this.filter { it.isDigit() }

    if (cpf.length != 11 || cpf.all { it == cpf[0] }) {
        return false
    }

    // D1
    var sum = 0
    for (i in 0..8) {
        sum += cpf[i].digitToInt() * (10 - i)
    }
    var d1 = 11 - (sum % 11)
    if (d1 >= 10) d1 = 0

    // D2
    sum = 0
    for (i in 0..9) {
        sum += cpf[i].digitToInt() * (11 - i)
    }
    var d2 = 11 - (sum % 11)
    if (d2 >= 10) d2 = 0

    return d1 == cpf[9].digitToInt() && d2 == cpf[10].digitToInt()
}


fun Relation.brName(): String {
    return when(this){
        Relation.FRIEND -> "Amigo(a)"
        Relation.MOTHER -> "Mãe"
        Relation.FATHER -> "Pai"
        Relation.RELATIVE -> "Parente"
        Relation.NEIGHBOR -> "Vizinho(a)"
    }
}