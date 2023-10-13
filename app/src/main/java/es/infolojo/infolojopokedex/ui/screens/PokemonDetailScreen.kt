package es.infolojo.infolojopokedex.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import es.infolojo.infolojopokedex.data.bussines.list.PokemonListBO
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme

@Composable
fun PokemonDetailScreen(pokemonBO: PokemonListBO, navController: NavController? = null) {

}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    InfolojoPokedexTheme {
        PokemonDetailScreen(PokemonListBO("Bulbasur", "example"))
    }
}