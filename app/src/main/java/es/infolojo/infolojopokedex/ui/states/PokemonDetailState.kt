package es.infolojo.infolojopokedex.ui.states

import es.infolojo.infolojopokedex.ui.vo.PokemonDetailVO

sealed interface PokemonDetailState {
    object Loading : PokemonDetailState
    data class Render(
        val pokemon: PokemonDetailVO
    ) : PokemonDetailState
}