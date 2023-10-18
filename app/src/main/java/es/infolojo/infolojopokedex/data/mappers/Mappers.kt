package es.infolojo.infolojopokedex.data.mappers

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.detail.PokemonsTypeBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListContainerBO
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR
import es.infolojo.infolojopokedex.data.remote.detail.PokemonDetailDTO
import es.infolojo.infolojopokedex.data.remote.detail.PokemonsTypeDTO
import es.infolojo.infolojopokedex.data.remote.list.PokemonListDTO
import es.infolojo.infolojopokedex.data.remote.list.PokemonsContainerDTO
import java.util.Locale

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
    image = this.sprites?.other?.officialArtWork?.frontDefault.orEmpty(),
    types = this.types.orEmpty().filterNotNull().toBO()
)

fun List<PokemonsTypeDTO>.toBO(): List<PokemonsTypeBO> = this.map { it.toBO() }

fun PokemonsTypeDTO.toBO(): PokemonsTypeBO = PokemonsTypeBO(
    name = this.type?.name.orEmpty(),
    typeUrl = this.type?.url.orEmpty(),
    color = this.type?.name.orEmpty().toPokemonColor()
)

fun String.toPokemonColor(): POKEMON_TYPE_COLOR = when (this.lowercase(Locale.ROOT)) {
    "normal" -> POKEMON_TYPE_COLOR.NORMAL
    "fire" -> POKEMON_TYPE_COLOR.FIRE
    "water" -> POKEMON_TYPE_COLOR.WATER
    "grass" -> POKEMON_TYPE_COLOR.GRASS
    "ice" -> POKEMON_TYPE_COLOR.ICE
    "fight" -> POKEMON_TYPE_COLOR.FIGHT
    "poison" -> POKEMON_TYPE_COLOR.POISON
    "ground" -> POKEMON_TYPE_COLOR.GROUND
    "flying" -> POKEMON_TYPE_COLOR.FLYING
    "psychic" -> POKEMON_TYPE_COLOR.PSYCHIC
    "bug" -> POKEMON_TYPE_COLOR.BUG
    "ghost" -> POKEMON_TYPE_COLOR.GHOST
    "dragon" -> POKEMON_TYPE_COLOR.DRAGON
    "dark" -> POKEMON_TYPE_COLOR.DARK
    "steel" -> POKEMON_TYPE_COLOR.STEEL
    "fairy" -> POKEMON_TYPE_COLOR.FAIRY
    else -> POKEMON_TYPE_COLOR.NORMAL
}
