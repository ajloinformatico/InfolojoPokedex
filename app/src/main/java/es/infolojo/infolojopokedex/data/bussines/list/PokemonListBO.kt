package es.infolojo.infolojopokedex.data.bussines.list

import java.io.Serializable

// TODO ADD id
data class PokemonListBO(
    val name: String,
    val detailUrl: String
) : Serializable