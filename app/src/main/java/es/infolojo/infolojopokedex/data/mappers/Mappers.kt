package es.infolojo.infolojopokedex.data.mappers

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListContainerBO
import es.infolojo.infolojopokedex.data.remote.detail.PokemonDetailDTO
import es.infolojo.infolojopokedex.data.remote.list.PokemonListDTO
import es.infolojo.infolojopokedex.data.remote.list.PokemonsContainerDTO

fun PokemonsContainerDTO.toBO(): PokemonListContainerBO = PokemonListContainerBO(
    nextUrl = this.next.orEmpty(),
    previousUrl = this.previous.orEmpty(),
    count = this.count?.toInt() ?: 0,
    pokemons = this.results.orEmpty().filterNotNull().map { it.toBO() },
    emptyList()
)

fun PokemonListDTO.toBO(): PokemonListBO = PokemonListBO(
    name = this.name.orEmpty(),
    detailUrl = this.url.orEmpty()
)

fun PokemonDetailDTO.toBo() = PokemonDetailBO(
    name = this.name.orEmpty(),
    id = this.id,
    image = this.sprites?.other?.officialArtWork?.frontDefault.orEmpty()
)
