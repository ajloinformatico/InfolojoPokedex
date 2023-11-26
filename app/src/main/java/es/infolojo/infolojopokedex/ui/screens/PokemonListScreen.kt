package es.infolojo.infolojopokedex.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.infolojo.infolojopokedex.ui.actions.AppActions
import es.infolojo.infolojopokedex.ui.components.Spinner
import es.infolojo.infolojopokedex.ui.components.TypesRow
import es.infolojo.infolojopokedex.ui.components.appBar.AppBar
import es.infolojo.infolojopokedex.ui.components.appBar.IconsManagerVO
import es.infolojo.infolojopokedex.ui.states.PokemonListState
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme
import es.infolojo.infolojopokedex.ui.viewmodel.PokemonListViewModel
import es.infolojo.infolojopokedex.ui.vo.PokemonVO
import es.infolojo.infolojopokedex.utils.extractPokemonIdFromUrl
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
    val pokemonsRenderVO = rememberSaveable { mutableStateOf(mutableListOf<PokemonVO>()) }

    Scaffold(
        topBar = {
            AppBar(
                title = SCREEN_NAME,
                startIcon = Icons.Filled.Close,
                onIconClick = { appActions(AppActions.CLOSEAPP) },
                iconsManagerVO = IconsManagerVO(
                    showFilter = false
                )
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {

                when (state.value) {
                    is PokemonListState.Loading -> {
                        Spinner(true)
                        pokemonListViewModel.init()
                    }

                    is PokemonListState.Render -> {
                        (state.value as? PokemonListState.Render)?.pokemnos?.let { pokemons ->
                            pokemonsRenderVO.value.addAll(pokemons)
                            Spinner(false)
                        }
                    }

                    else -> {
                        Log.d("TonyTest", "Can not read nothing")
                    }
                }

                PokemonRecyclerView(pokemonsRenderVO.value) { pokemonDetailUrl ->
                    Log.d(
                        "TonyTest",
                        "pokemonListScreen onLick ${pokemonDetailUrl.extractPokemonIdFromUrl()}"
                    )
                    navController?.let {
                        pokemonListViewModel.navigateToPokemonKey(pokemonDetailUrl, it)
                    }
                }
            }
        }
    )
}


@Composable
fun PokemonRecyclerView(
    pokemons: List<PokemonVO>,
    onClick: (String) -> Unit
) {
    LazyColumn {
        items(pokemons.size) { index ->
            pokemons.getOrNull(index)?.let { pokemon ->
                PokemonListVieHolder(pokemonVO = pokemon, index = index + 1, onClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonListVieHolder(pokemonVO: PokemonVO, index: Int, onClick: (String) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            onClick(pokemonVO.detailUrl)
        }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)

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


                // Pokemon type Loaded with a common component
                TypesRow(types = type)
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
