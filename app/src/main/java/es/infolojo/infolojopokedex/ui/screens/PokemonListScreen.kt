package es.infolojo.infolojopokedex.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.infolojo.infolojopokedex.ui.actions.AppActions
import es.infolojo.infolojopokedex.ui.components.AppBar
import es.infolojo.infolojopokedex.ui.states.PokemonListState
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme
import es.infolojo.infolojopokedex.ui.viewmodel.PokemonListViewModel
import es.infolojo.infolojopokedex.ui.vo.PokemonVO
import es.infolojo.infolojopokedex.utils.toCustomCapitalize


private const val SCREEN_NAME = "Pokedex"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    navController: NavController? = null,
    appActions: (AppActions) -> Unit,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {
    val state: State<PokemonListState?> = pokemonListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                title = SCREEN_NAME,
                imageVector = Icons.Filled.Close
            ) {
                appActions(AppActions.CLOSEAPP)
            }
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                
                // TODO ADD INIT VIEWMODEL METHOD
                when (state.value) {
                    is PokemonListState.Loading -> {
                        Log.d("TonyTest", "Load")
                    }
                    is PokemonListState.Render -> {
                        (state.value as? PokemonListState.Render)?.pokemnos?.let { pokemons ->
                            Log.d("TonyTest", "render ${pokemons}")
                            PokemonRecyclerView(pokemons)
                        }
                    }
                    else -> {
                        Log.d("TonyTest","Can not read nothing")
                    }
                }
                pokemonListViewModel.init()
            }
        }
    )
}



@Composable
fun PokemonRecyclerView(
    pokemons: List<PokemonVO>
) {
    LazyColumn {
        items(pokemons.size) { index ->
            pokemons.getOrNull(index)?.let { pokemon ->
                PokemonListVieHolder(pokemonVO = pokemon, index = index + 1)
            }
        }
    }
}

@Composable
private fun PokemonListVieHolder(pokemonVO: PokemonVO, index: Int) {
    // TODO CONTINUE HERE ADDING POKEMON IMAGE
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        with(pokemonVO) {
            // Pokemon name
            Row {
                Text(
                    text = name.toCustomCapitalize(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "# $index",
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            // Pokemon image
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = image,
                contentDescription = "$name image"
            )


            // Pokemon type
            Column {
                Text(text = "Types", style = MaterialTheme.typography.titleMedium)
                Row {
                    type.map {
                        Column(modifier = Modifier.padding(end = 8.dp, top = 4.dp)) {
                            Text(
                                text = it.name,
                                modifier = Modifier
                                    .background(
                                        color = it.color.colorValue
                                    )
                                    .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview() {
    InfolojoPokedexTheme {
        PokemonListScreen(
            navController = null,
            pokemonListViewModel = hiltViewModel(),
            appActions = {}
        )
    }
}