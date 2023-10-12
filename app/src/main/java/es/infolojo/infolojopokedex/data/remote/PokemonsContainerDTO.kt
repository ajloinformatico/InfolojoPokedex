package es.infolojo.infolojopokedex.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonsContainerDTO(
    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: String?,

    @SerializedName("count")
    val count: Long?,

    @SerializedName("results")
    val results: List<PokemonDTO?>?
) : Serializable
