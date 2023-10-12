package es.infolojo.infolojopokedex.ui.states

import es.infolojo.infolojopokedex.ui.vo.PokemonVO

sealed interface PokemonListState {
    object Loading: PokemonListState
    data class Render(
        val pokemnos: List<PokemonVO>
    ): PokemonListState
}