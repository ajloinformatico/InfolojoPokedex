package es.infolojo.infolojopokedex.data.repository

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListContainerBO
import es.infolojo.infolojopokedex.data.mappers.toBO
import es.infolojo.infolojopokedex.data.mappers.toBo
import es.infolojo.infolojopokedex.data.remote.RemoteService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteRepository {

    override suspend fun getPokemons(): PokemonListContainerBO? {
        remoteService.getAllPokemonsResource().takeIf { it.isSuccessful }?.body()?.toBO()?.let {

            val pokemonsDetailBO: List<PokemonDetailBO> = it.pokemons.mapNotNull { pokemonListBO ->
                val id: Long =
                    pokemonListBO.detailUrl.split("pokemon").last().split("/").get(1).toLong()
                remoteService.getPokemonDetail(id).takeIf { it.isSuccessful }?.body()?.toBo()
            }

            return PokemonListContainerBO(
                nextUrl = it.nextUrl,
                previousUrl = it.previousUrl,
                count = it.count,
                pokemons = it.pokemons,
                pokemonsDetailBO = pokemonsDetailBO
            )
        } ?: run { return null }
    }

    override suspend fun getPokemonDetail(id: Long): PokemonDetailBO? {
        return remoteService.getPokemonDetail(id).takeIf { it.isSuccessful }?.body()?.toBo()
    }
}
