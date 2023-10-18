package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonDetailDTO(
    @SerializedName("id")
    val id: Long = -1L,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val sprites: PokemonSpritesDTO?,
    @SerializedName("types")
    val types: List<PokemonsTypeDTO?>?
) : Serializable

data class PokemonsTypeDTO(
    @SerializedName("slot")
    val slot: Long = -1L,
    @SerializedName("type")
    val type: PokemonsTypeInDetailDTO?
) : Serializable

data class PokemonsTypeInDetailDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
) : Serializable
