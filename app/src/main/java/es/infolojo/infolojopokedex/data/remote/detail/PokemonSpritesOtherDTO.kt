package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonSpritesOtherDTO(
    @SerializedName("official-artwork")
    val officialArtWorkDTO: OtherSpritesContentDTO?,
    @SerializedName("home")
    val homeSpritesDTO: OtherSpritesContentDTO?,
    @SerializedName("dream_world")
    val dreamWorldSpritesDTO: OtherSpritesContentDTO?
) : Serializable
