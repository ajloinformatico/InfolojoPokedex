package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonsTypeDTO(
    @SerializedName("slot")
    val slot: Long = -1L,
    @SerializedName("type")
    val type: PokemonsTypeInDetailDTO?
) : Serializable
