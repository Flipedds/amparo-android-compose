package com.table.tatu.amparo.extensions

fun String.isEqualsAndNotBlank(string: String): Boolean {
    return this == string && this.isNotBlank()
}

fun String.isDifferent(string: String) : Boolean {
    return string != this
}

fun String.isPassValid(): Boolean{
    return this.matches(Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"))
}