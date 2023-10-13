package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonSpritesDTO(
    @SerializedName("other")
    val other: PokemonSpritesOtherDTO?
) : Serializable
