package es.infolojo.infolojopokedex.data.repository

import es.infolojo.infolojopokedex.data.bussines.PokemonContainerBO
import es.infolojo.infolojopokedex.data.mappers.toBO
import es.infolojo.infolojopokedex.data.remote.RemoteService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteRepository {

    override suspend fun getPokemons(): PokemonContainerBO? {
        return remoteService.getAllPokemonsResource().takeIf { it.isSuccessful }?.body()?.toBO()
    }
}