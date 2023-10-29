package es.infolojo.infolojopokedex.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.infolojo.infolojopokedex.ui.components.Spinner
import es.infolojo.infolojopokedex.ui.components.appBar.AppBar
import es.infolojo.infolojopokedex.ui.components.appBar.IconsManagerVO
import es.infolojo.infolojopokedex.ui.states.PokemonDetailState
import es.infolojo.infolojopokedex.ui.viewmodel.PokemonDetailViewModel
import es.infolojo.infolojopokedex.ui.vo.PokemonDetailVO
import es.infolojo.infolojopokedex.utils.toCustomCapitalize


private const val SCREEN_NAME = "Pokedex Detail"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    pokemonDetailKey: Long,
    navController: NavController? = null,
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state: State<PokemonDetailState?> = pokemonDetailViewModel.uiState.collectAsState()
    val pokemonDetailRenderVO = rememberSaveable { mutableStateOf<PokemonDetailVO?>(null) }
    val pokemonName = rememberSaveable { mutableStateOf<String>(SCREEN_NAME) }
    val pokemonNumber = rememberSaveable { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            AppBar(
                title = pokemonName.value,
                startIcon = Icons.Filled.ArrowBack,
                onIconClick = {
                    navController?.navigateUp()
                },
                iconsManagerVO = IconsManagerVO(
                    showFilter = false,
                    showPokemonNumber = true
                ),
                pokemonNumber = pokemonNumber.value
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                val viewState = state.value
                when (viewState) {
                    is PokemonDetailState.Loading -> {
                        Spinner(true)
                        pokemonDetailViewModel.init(pokemonDetailKey)
                    }
                    is PokemonDetailState.Render -> {
                        with (viewState.pokemon) {
                            pokemonName.value = this.name.toCustomCapitalize()
                            pokemonNumber.value = "# ${this.id}"
                            pokemonDetailRenderVO.value = this
                        }
                        Spinner(false)
                    }

                    else -> {
                        // no_op
                    }
                }

                PokemonDetailContent(pokemon = pokemonDetailRenderVO.value, pokemonName.value)
            }
        }
    )
}

@Composable
fun PokemonDetailContent(
    pokemon: PokemonDetailVO?,
    pokemonName: String
) {
    pokemon ?: return
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        with(pokemon) {
            // Pokemon image
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = image,
                contentDescription = "$pokemonName image"
            )
            Text(
                text = "loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

/**
@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    InfolojoPokedexTheme {
        PokemonDetailScreen()
    }
}
        */