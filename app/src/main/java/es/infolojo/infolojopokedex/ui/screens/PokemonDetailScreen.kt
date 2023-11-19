package es.infolojo.infolojopokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR
import es.infolojo.infolojopokedex.ui.components.Spinner
import es.infolojo.infolojopokedex.ui.components.TypesRow
import es.infolojo.infolojopokedex.ui.components.appBar.AppBar
import es.infolojo.infolojopokedex.ui.components.appBar.IconsManagerVO
import es.infolojo.infolojopokedex.ui.states.PokemonDetailState
import es.infolojo.infolojopokedex.ui.viewmodel.PokemonDetailViewModel
import es.infolojo.infolojopokedex.ui.vo.PokemonDetailVO
import es.infolojo.infolojopokedex.ui.vo.PokemonTypeVO
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
                pokemonNumber = pokemonNumber.value,
                backGround = pokemonDetailRenderVO.value?.types?.firstOrNull()?.color?.colorValue
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding() - 10.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
            ) {
                when (val viewState = state.value) {
                    is PokemonDetailState.Loading -> {
                        Spinner(true)
                        pokemonDetailViewModel.init(pokemonDetailKey)
                    }

                    is PokemonDetailState.Render -> {
                        with(viewState.pokemon) {
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

// region content
@Composable
fun PokemonDetailContent(
    pokemon: PokemonDetailVO?,
    pokemonName: String
) {
    pokemon ?: return
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        with(pokemon) {
            DetailImagesRecyclerView(
                images = images,
                pokemonName = pokemonName,
                type = types.firstOrNull()
            )
            ScrollableContent(this)
        }
    }
}
// endregion content

// region images lazy row
@Composable
fun DetailImagesRecyclerView(
    images: List<String>,
    pokemonName: String,
    type: PokemonTypeVO?
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(
                color = type?.color?.colorValue ?: POKEMON_TYPE_COLOR.NORMAL.colorValue,
                shape = RoundedCornerShape(5)
            )
    ) {
        items(images.size) { index ->
            images.getOrNull(index)?.let { singleImage ->
                DetailImagesRecyclerViewHolder(
                    image = singleImage,
                    contentDescription = "Pokemon $pokemonName $index"
                )
            }
        }
    }
}

@Composable
fun DetailImagesRecyclerViewHolder(
    image: String,
    contentDescription: String
) {
    val configuration = LocalConfiguration.current
    val widthInDP = configuration.screenWidthDp.dp
    val heightInDP = configuration.screenHeightDp.dp
    val screenTerciarPar = heightInDP / 3
    val screenQuarterPart = widthInDP / 4

    Column(
        modifier = Modifier
            .width(widthInDP - screenQuarterPart)
            .height(screenTerciarPar)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(1F),
            model = image,
            contentDescription = contentDescription
        )
    }
}

// endregion images lazy row

// region scrollableContent
@Composable
fun ScrollableContent(
    pokemon: PokemonDetailVO
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        TypesRow(
            types = pokemon.types,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            titleStyle = MaterialTheme.typography.titleLarge
        )

        StatsRecyclerComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            stats = pokemon.stats,
            backGroundColor = pokemon.types.firstOrNull()?.color?.colorValue
        )

        Column {
            Text(
                text = "More information",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum loren ipsum loren ipsum loren ipsum  loren ipsum  loren ipsum  loren ipsum",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
// endregion scrollable content

/** TODO FIX THIS WARNING
@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
InfolojoPokedexTheme {
PokemonDetailScreen()
}
}
 */
