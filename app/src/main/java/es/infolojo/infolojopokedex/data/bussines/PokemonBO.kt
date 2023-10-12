package es.infolojo.infolojopokedex.data.bussines

import java.io.Serializable

data class PokemonBO(
    val name: String,
    val detailUrl: String
) : Serializable