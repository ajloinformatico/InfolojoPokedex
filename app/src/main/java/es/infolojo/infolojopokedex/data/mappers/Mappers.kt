package es.infolojo.infolojopokedex.data.mappers

import es.infolojo.infolojopokedex.data.bussines.PokemonBO
import es.infolojo.infolojopokedex.data.bussines.PokemonContainerBO
import es.infolojo.infolojopokedex.data.remote.PokemonDTO
import es.infolojo.infolojopokedex.data.remote.PokemonsContainerDTO

fun PokemonsContainerDTO.toBO(): PokemonContainerBO = PokemonContainerBO(
    nextUrl = this.next.orEmpty(),
    previousUrl = this.previous.orEmpty(),
    count = this.count?.toInt() ?: 0,
    pokemons = this.results.orEmpty().filterNotNull().map { it.toBO() }
)

fun PokemonDTO.toBO(): PokemonBO = PokemonBO(
    name = this.name.orEmpty(),
    detailUrl = this.url.orEmpty()
)
