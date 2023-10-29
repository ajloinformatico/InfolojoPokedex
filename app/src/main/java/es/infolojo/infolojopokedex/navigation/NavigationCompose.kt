package es.infolojo.infolojopokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.infolojo.infolojopokedex.ui.actions.AppActions
import es.infolojo.infolojopokedex.ui.screens.PokemonDetailScreen
import es.infolojo.infolojopokedex.ui.screens.PokemonListScreen

const val POKEMONS_LIST_ROUTE = "pokemonList"
const val POKEMON_DETAIL_ROUTE = "pokemonDetail"
const val POKEMON_DETAIL_KEY = "pokemonDetailUrl"

@Composable
fun NavigationCompose(appActions: (AppActions) -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = POKEMONS_LIST_ROUTE) {
        composable(
            route = "pokemonDetail/{$POKEMON_DETAIL_KEY}",
            arguments = listOf(
                navArgument(POKEMON_DETAIL_KEY) {
                    type = NavType.LongType
                }
            )
        ) { navBackStackEntry ->
            PokemonDetailScreen(
                pokemonDetailKey = navBackStackEntry.arguments?.getLong(POKEMON_DETAIL_KEY) ?: -1L,
                navController = navController
            )
        }

        composable(route = POKEMONS_LIST_ROUTE) {
            PokemonListScreen(
                navController = navController,
                appActions = appActions
            )
        }
    }
}
