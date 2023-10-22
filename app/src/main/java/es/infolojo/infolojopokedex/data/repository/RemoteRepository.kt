package es.infolojo.infolojopokedex.data.repository

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListContainerBO
import es.infolojo.infolojopokedex.data.remote.detail.PokemonDetailDTO
import retrofit2.Response
import retrofit2.http.Query

interface RemoteRepository {
    suspend fun getPokemons(): PokemonListContainerBO?
    suspend fun getNextPokemons(offset: Int, limit: Int): PokemonListContainerBO?
    suspend fun getPokemonDetail(id: Long): PokemonDetailBO?
}