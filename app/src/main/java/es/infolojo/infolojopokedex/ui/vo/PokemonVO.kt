package es.infolojo.infolojopokedex.ui.vo

import es.infolojo.infolojopokedex.data.bussines.PokemonBO

data class PokemonVO(
    val name: String,
    val image: String,
    val detailUrl: String
)

fun PokemonBO.toVO(): PokemonVO = PokemonVO(
    name = this.name,
    image = "",
    detailUrl = this.detailUrl
)