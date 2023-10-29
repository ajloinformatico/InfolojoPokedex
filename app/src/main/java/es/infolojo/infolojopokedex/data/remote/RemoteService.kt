package es.infolojo.infolojopokedex.data.remote

import es.infolojo.infolojopokedex.data.remote.detail.PokemonDetailDTO
import es.infolojo.infolojopokedex.data.remote.list.PokemonsContainerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val ALL_POKEMONS_URL = "pokemon"

interface RemoteService {

    @GET(ALL_POKEMONS_URL)
    suspend fun getInitialPokemonsResource(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 50
    ): Response<PokemonsContainerDTO>

    @GET(ALL_POKEMONS_URL)
    suspend fun getNextPokemonsResource(
        @Query("offset") offset: Int = 51,
        @Query("limit") limit: Int = 10000
    ) : Response<PokemonsContainerDTO>

    @GET("$ALL_POKEMONS_URL/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Long): Response<PokemonDetailDTO>
}
