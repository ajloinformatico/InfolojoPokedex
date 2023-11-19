package es.infolojo.infolojopokedex.data.bussines.detail

import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR

data class PokemonsTypeBO(
    val name: String,
    val typeUrl: String,
    val color: POKEMON_TYPE_COLOR
)
