package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import es.infolojo.infolojopokedex.utils.takeIfIsNotAnSVGOrNull
import java.io.Serializable

data class PokemonSpritesDTO(
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("other")
    val other: PokemonSpritesOtherDTO?
) : Serializable

fun PokemonSpritesDTO.getAllImages(): List<String> {
    val images: MutableList<String?> = mutableListOf()
    images.addAll(
        listOf(
            other?.officialArtWorkDTO?.frontDefault?.takeIfIsNotAnSVGOrNull(),
            other?.officialArtWorkDTO?.frontShiny?.takeIfIsNotAnSVGOrNull(),
            other?.dreamWorldSpritesDTO?.frontDefault?.takeIfIsNotAnSVGOrNull(),
            other?.dreamWorldSpritesDTO?.frontShiny?.takeIfIsNotAnSVGOrNull(),
            frontDefault?.takeIfIsNotAnSVGOrNull(),
            backDefault?.takeIfIsNotAnSVGOrNull(),
            frontShiny?.takeIfIsNotAnSVGOrNull(),
            backShiny?.takeIfIsNotAnSVGOrNull(),
            other?.homeSpritesDTO?.frontDefault?.takeIfIsNotAnSVGOrNull(),
            other?.homeSpritesDTO?.frontShiny?.takeIfIsNotAnSVGOrNull()
        )
    )
    return images.filterNotNull().toList()
}
