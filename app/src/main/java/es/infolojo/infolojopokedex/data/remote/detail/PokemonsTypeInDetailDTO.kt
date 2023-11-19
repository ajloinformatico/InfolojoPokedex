package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonsTypeInDetailDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
) : Serializable
