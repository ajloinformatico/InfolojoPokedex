package es.infolojo.infolojopokedex.utils

import java.util.Locale

fun String.toCustomCapitalize() = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }