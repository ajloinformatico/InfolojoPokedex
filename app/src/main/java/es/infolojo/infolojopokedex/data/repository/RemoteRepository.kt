package es.infolojo.infolojopokedex.data.repository

import es.infolojo.infolojopokedex.data.bussines.PokemonContainerBO

interface RemoteRepository {
    suspend fun getPokemons(): PokemonContainerBO?
}