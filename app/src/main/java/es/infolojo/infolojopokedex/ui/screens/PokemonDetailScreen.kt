package es.infolojo.infolojopokedex.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import es.infolojo.infolojopokedex.Greeting
import es.infolojo.infolojopokedex.data.bussines.PokemonBO
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme

@Composable
fun PokemonDetailScreen(pokemonBO: PokemonBO, navController: NavController? = null) {

}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    InfolojoPokedexTheme {
        PokemonDetailScreen(PokemonBO("Bulbasur", "example"))
    }
}