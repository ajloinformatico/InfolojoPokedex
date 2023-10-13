package es.infolojo.infolojopokedex.data.remote.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonListDTO(
    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?

) : Serializable
