package es.infolojo.infolojopokedex.data.bussines.list

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO

data class PokemonListContainerBO(
    val nextUrl: String,
    val previousUrl: String,
    val count: Int,
    val pokemons: List<PokemonListBO>,
    val pokemonsDetailBO: List<PokemonDetailBO>
)