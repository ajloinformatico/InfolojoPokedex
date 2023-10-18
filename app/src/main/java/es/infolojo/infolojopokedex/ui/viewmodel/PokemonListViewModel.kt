package es.infolojo.infolojopokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListContainerBO
import es.infolojo.infolojopokedex.data.repository.RemoteRepositoryImpl
import es.infolojo.infolojopokedex.ui.states.PokemonListState
import es.infolojo.infolojopokedex.ui.vo.toVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    val remoteRepository: RemoteRepositoryImpl
) : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow<PokemonListState?>(null)
    val uiState: StateFlow<PokemonListState?> = _uiState.asStateFlow()

    init {
        _uiState.value = PokemonListState.Loading
    }

    fun init() {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            val pokemonsContainerBO: PokemonListContainerBO? = remoteRepository.getPokemons()
            val pokemonsVO = pokemonsContainerBO?.pokemons?.mapIndexed { index, pokemon ->
                pokemon.toVO(
                    image = pokemonsContainerBO.pokemonsDetailBO.getOrNull(index)?.image.orEmpty(),
                    type = pokemonsContainerBO.pokemonsDetailBO.getOrNull(index)?.types.orEmpty().toVO()
                )
            }.orEmpty()

            _uiState.value = PokemonListState.Render(
                pokemnos = pokemonsVO
            )

        }
    }
}