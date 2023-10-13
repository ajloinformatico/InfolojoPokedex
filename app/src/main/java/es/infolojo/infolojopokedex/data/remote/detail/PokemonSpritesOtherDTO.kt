package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonSpritesOtherDTO(
    @SerializedName("official-artwork")
    val officialArtWork: OfficialArtWork?
) : Serializable
