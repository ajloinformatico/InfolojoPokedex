package es.infolojo.infolojopokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.repository.RemoteRepositoryImpl
import es.infolojo.infolojopokedex.ui.states.PokemonDetailState
import es.infolojo.infolojopokedex.ui.vo.toPokemonDetailVO
import es.infolojo.infolojopokedex.utils.extractPokemonIdFromUrl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val remoteRepository: RemoteRepositoryImpl
): ViewModel() {
    private val _uiState = MutableStateFlow<PokemonDetailState?>(null)
    val uiState: StateFlow<PokemonDetailState?> = _uiState.asStateFlow()

    init {
        _uiState.value = PokemonDetailState.Loading
    }

    private var pokemonDetailBO: PokemonDetailBO? = null

    fun init(pokemonDetailId: Long) {
        getPokemon(pokemonDetailId)
    }

    private fun getPokemon(pokemonDetailId: Long) {
        viewModelScope.launch {
            remoteRepository.getPokemonDetail(pokemonDetailId)?.let {
                pokemonDetailBO = it
            }
            loadPokemon()
        }
    }

    private fun loadPokemon() {
        pokemonDetailBO?.let {
            _uiState.value = PokemonDetailState.Render(
                it.toPokemonDetailVO()
            )
        }
    }
}
