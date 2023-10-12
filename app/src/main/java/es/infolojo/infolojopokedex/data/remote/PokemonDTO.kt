package es.infolojo.infolojopokedex.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonDTO(
    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?

) : Serializable
