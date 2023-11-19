package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonStatDTO(
    @SerializedName("base_stat")
    val statPoint: Int?,
    @SerializedName("effort")
    val effort: Int?,
    @SerializedName("stat")
    val statDetail: PokemonStatDetailDTO?
) : Serializable
