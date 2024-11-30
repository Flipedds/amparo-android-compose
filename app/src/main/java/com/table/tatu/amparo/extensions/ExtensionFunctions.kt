package com.table.tatu.amparo.extensions

import com.table.tatu.amparo.enums.Relation
import kotlinx.coroutines.yield

fun String.isEqualsAndNotBlank(string: String): Boolean {
    return this == string && this.isNotBlank()
}

fun String.isDifferent(string: String) : Boolean {
    return string != this
}

fun String.isPassValid(): Boolean{
    return this.matches(Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"))
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