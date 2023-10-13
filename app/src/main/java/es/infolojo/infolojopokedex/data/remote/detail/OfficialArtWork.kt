package es.infolojo.infolojopokedex.data.remote.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OfficialArtWork(
    @SerializedName("front_default")
    val frontDefault: String?
) : Serializable
