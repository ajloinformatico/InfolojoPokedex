package es.infolojo.infolojopokedex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListContainerBO
import es.infolojo.infolojopokedex.data.repository.RemoteRepositoryImpl
import es.infolojo.infolojopokedex.ui.states.PokemonListState
import es.infolojo.infolojopokedex.ui.vo.PokemonVO
import es.infolojo.infolojopokedex.ui.vo.toVO
import es.infolojo.infolojopokedex.utils.extractPokemonIdFromUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.Key
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    val remoteRepository: RemoteRepositoryImpl
) : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow<PokemonListState?>(null)
    val uiState: StateFlow<PokemonListState?> = _uiState.asStateFlow()

    private val _pokemonsVO = mutableListOf<PokemonVO>()

    init {
        _uiState.value = PokemonListState.Loading
    }

    fun init() {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            val firstLoad = async {
                val pokemonsContainerBO: PokemonListContainerBO? = remoteRepository.getPokemons()
                renderPokemons(pokemonsContainerBO)
                pokemonsContainerBO?.nextUrl
            }
            getNextPokemons(firstLoad.await().orEmpty())
        }
    }


    private fun extractOffSetAndLimitFromUrl(url: String): List<Int> {
        val newUrl = url.split("?offset=").lastOrNull()
        return try {
            val offset = newUrl?.split("&limit=")?.firstOrNull()?.toInt()
            val limit = newUrl?.split("&limit=")?.lastOrNull()?.toInt()
            if (offset != null && limit != null) {
                listOf(offset, limit)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("Error: extractOffSetAndLimitFromUrl", e.message.orEmpty())
            emptyList()
        }
    }

    private suspend fun getNextPokemons(url: String) {
        val pokemonsContainerBO = viewModelScope.async {
            extractOffSetAndLimitFromUrl(url = url).takeIf { it.isNotEmpty() }?.let {
                remoteRepository.getNextPokemons(it[0], it[1])
            }
        }

        pokemonsContainerBO.await()?.let { nextPokemonContainer ->
            renderPokemons(nextPokemonContainer)
            nextPokemonContainer.nextUrl.takeIf { it.isNotEmpty() }?.let { nextUrl ->
                getNextPokemons(nextUrl)
            }
        }

    }

    private fun renderPokemons(pokemonsContainerBO: PokemonListContainerBO?) {
        pokemonsContainerBO?.pokemons ?: return

        val pokemonsVO = pokemonsContainerBO.pokemons.mapIndexed { index, pokemon ->
            pokemon.toVO(
                image = pokemonsContainerBO.pokemonsDetailBO.getOrNull(index)?.images?.firstOrNull().orEmpty(),
                type = pokemonsContainerBO.pokemonsDetailBO.getOrNull(index)?.types.orEmpty().toVO()
            )
        }

        _pokemonsVO.addAll(pokemonsVO)
        _uiState.value = PokemonListState.Render(
            pokemnos = pokemonsVO
        )
    }

    // region navigate
    fun navigateToPokemonKey(pokemonUrl: String, navController: NavController) {
        navController.navigate("pokemonDetail/${pokemonUrl.extractPokemonIdFromUrl()}")
    }
}
