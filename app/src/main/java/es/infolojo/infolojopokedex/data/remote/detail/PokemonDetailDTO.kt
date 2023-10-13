package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonDetailDTO(
    @SerializedName("id")
    val id: Long = -1L,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val sprites: PokemonSpritesDTO?
) : Serializable
