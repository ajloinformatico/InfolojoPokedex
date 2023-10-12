package es.infolojo.infolojopokedex.data.remote

import retrofit2.Response
import retrofit2.http.GET

private const val ALL_POKEMONS_URL = "pokemon"

interface RemoteService {

    @GET(ALL_POKEMONS_URL)
    suspend fun getAllPokemonsResource(): Response<PokemonsContainerDTO>
}
