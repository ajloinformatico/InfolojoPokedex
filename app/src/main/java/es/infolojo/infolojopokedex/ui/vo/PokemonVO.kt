package es.infolojo.infolojopokedex.ui.vo

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListBO

data class PokemonVO(
    val name: String,
    val image: String,
    val detailUrl: String
)

fun PokemonListBO.toVO(image: String): PokemonVO = PokemonVO(
    name = this.name,
    image = image,
    detailUrl = this.detailUrl
)
