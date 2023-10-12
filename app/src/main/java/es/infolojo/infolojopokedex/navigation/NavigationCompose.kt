package es.infolojo.infolojopokedex.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.infolojo.infolojopokedex.data.bussines.PokemonBO
import es.infolojo.infolojopokedex.ui.actions.AppActions
import es.infolojo.infolojopokedex.ui.screens.PokemonDetailScreen
import es.infolojo.infolojopokedex.ui.screens.PokemonListScreen
import es.infolojo.infolojopokedex.ui.viewmodel.PokemonListViewModel

const val POKEMONS_LIST_ROUTE = "POKEMONS_LIST_ROUTE"
const val POKEMON_DETAIL_ROUTE = "POKEMON_DETAIL_ROUTE"
const val POKEMON_ID_KEY = "pokemonId"

@Composable
fun NavigationCompose(appActions: (AppActions) -> Unit ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = POKEMONS_LIST_ROUTE) {
        composable(route = POKEMONS_LIST_ROUTE) {
            // TODO VIEWMODEL INJECT
            PokemonListScreen(
                navController = navController,
                appActions = appActions
            )
        }

        composable(
            route = "$POKEMON_DETAIL_ROUTE/$POKEMON_ID_KEY",
            arguments = listOf(navArgument(POKEMON_ID_KEY) {
                type = NavType.SerializableType(PokemonBO::class.java)
            })
        ) { navBackStackEntry ->

            val pokemonBO: PokemonBO? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                navBackStackEntry.arguments?.getSerializable(POKEMON_ID_KEY, PokemonBO::class.java)
            } else {
                (navBackStackEntry.arguments?.getSerializable(POKEMON_ID_KEY) as PokemonBO?)
            }

            pokemonBO?.let {
                PokemonDetailScreen(
                    pokemonBO = it,
                    navController = navController
                )
            }
        }
    }
}
