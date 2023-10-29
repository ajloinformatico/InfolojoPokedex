package es.infolojo.infolojopokedex.utils

import android.util.Log
import java.util.Locale

private const val POKEMON_SPLIT = "pokemon"
private const val SLICE_SPLIT = "/"
private const val DEFAULT_LONG = -1L
private const val CLASS_NAME = "StringExtensions"

fun String.toCustomCapitalize() = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }


fun String.extractPokemonIdFromUrl() : Long = this.split(POKEMON_SPLIT).lastOrNull()?.split(SLICE_SPLIT)?.getOrNull(1).orEmpty().toLongOrDefault()


fun String.toLongOrDefault(default: Long = DEFAULT_LONG): Long {
    return try {
        this.toLong()
    } catch (e: Exception) {
        Log.d(CLASS_NAME, e.toString())
        default
    }
}