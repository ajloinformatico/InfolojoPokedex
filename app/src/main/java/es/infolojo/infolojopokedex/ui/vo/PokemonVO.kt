package es.infolojo.infolojopokedex.ui.vo

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.detail.PokemonsTypeBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListBO
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR

data class PokemonVO(
    val name: String,
    val image: String,
    val detailUrl: String,
    val type: List<PokemonTypeVO>
)

data class PokemonTypeVO(
    val name: String,
    val typeUrl: String,
    val color: POKEMON_TYPE_COLOR
)

fun PokemonListBO.toVO(image: String, type: List<PokemonTypeVO>): PokemonVO = PokemonVO(
    name = this.name,
    image = image,
    detailUrl = this.detailUrl,
    type = type
)

fun List<PokemonsTypeBO>.toVO() : List<PokemonTypeVO> = this.map { it.toVO() }

fun PokemonsTypeBO.toVO(): PokemonTypeVO = PokemonTypeVO(
    name = this.name,
    typeUrl = this.typeUrl,
    color = this.color
)