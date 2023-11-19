package es.infolojo.infolojopokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme
import es.infolojo.infolojopokedex.ui.vo.PokemonTypeVO

private const val TYPES_TITLE = "Types"

@Composable
fun TypesRow(
    modifier: Modifier = Modifier,
    types: List<PokemonTypeVO>,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium
) {
    Column(modifier = modifier) {
        Text(text = TYPES_TITLE, style = titleStyle)
        Row {
            types.map {
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

@Preview(showBackground = true)
@Composable
fun TypesRowPreview() {
    InfolojoPokedexTheme {
        TypesRow(
            modifier = Modifier.fillMaxWidth(),
            types = listOf(
                PokemonTypeVO("grass", "", POKEMON_TYPE_COLOR.GRASS),
                PokemonTypeVO("poison", "", POKEMON_TYPE_COLOR.POISON),
                PokemonTypeVO("fire", "", POKEMON_TYPE_COLOR.FIRE),
            )
        )
    }
}
