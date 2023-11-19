package es.infolojo.infolojopokedex.utils

fun Int?.getOrZero(): Int = this ?: 0

fun Int?.getOrDefaultOrZero(default: Int?): Int = this ?: default ?: getOrZero()
