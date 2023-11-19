package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonStatDetailDTO(
    @SerializedName("name")
    val statsName: String?,
    @SerializedName("url")
    val statsUrl: String?
) : Serializable
