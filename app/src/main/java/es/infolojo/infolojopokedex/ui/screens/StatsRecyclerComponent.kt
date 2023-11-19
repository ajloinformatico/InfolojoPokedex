package es.infolojo.infolojopokedex.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme

private const val STATS_TITLE = "Stats"

data class PokemonStatsVO(
    val title: String,
    val stat: Int,
    val color: POKEMON_TYPE_COLOR
)

@Composable
fun StatsRecyclerComponent(
    modifier: Modifier,
    stats: List<PokemonStatsVO>,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    subTitleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    backGroundColor: Color?
) {
    Column(modifier = modifier) {
        Text(
            text = STATS_TITLE,
            style = titleStyle
        )

        stats.map { stat ->
            StatsComponentViewHolder(
                modifier = Modifier.padding(
                    top = 4.dp
                ),
                stat = stat,
                subTitleStyle = subTitleStyle,
                backGroundColor = backGroundColor
            )
        }
    }
}

@Composable
fun StatsComponentViewHolder(
    modifier: Modifier,
    stat: PokemonStatsVO,
    subTitleStyle: TextStyle,
    backGroundColor: Color?
) {
    val sliderPosition by remember { mutableIntStateOf(stat.stat) }
    Column(
        modifier = modifier
    ) {
        Text(
            text = "${stat.title} $sliderPosition",
            style = subTitleStyle,
            color = stat.color.colorValue
        )
        Slider(
            value = sliderPosition.toFloat(),
            onValueChange = { /* no-op */ },
            colors = SliderDefaults.colors(
                thumbColor = stat.color.colorValue,
                activeTrackColor = stat.color.colorValue,
                inactiveTrackColor = backGroundColor
                    ?: MaterialTheme.colorScheme.secondaryContainer,
            ),
            valueRange = 0f..100f
        )
    }
}


@Composable
@Preview(showBackground = true)
fun StatsComponentPreview() {
    val stats = listOf(
        PokemonStatsVO(
            title = "Fuerza",
            color = POKEMON_TYPE_COLOR.FIRE,
            stat = 50
        ),
        PokemonStatsVO(
            title = "Ejemplo",
            color = POKEMON_TYPE_COLOR.POISON,
            stat = 25
        ),
        PokemonStatsVO(
            title = "Example",
            color = POKEMON_TYPE_COLOR.FAIRY,
            stat = 98
        ),

        )
    InfolojoPokedexTheme {
        StatsRecyclerComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            stats = stats,
            titleStyle = MaterialTheme.typography.titleLarge,
            backGroundColor = POKEMON_TYPE_COLOR.GROUND.colorValue
        )
    }
}
