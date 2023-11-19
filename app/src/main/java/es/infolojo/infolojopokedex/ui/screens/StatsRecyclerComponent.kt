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
import es.infolojo.infolojopokedex.data.common.POKEMON_STAT_COLOR
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme
import es.infolojo.infolojopokedex.ui.vo.StatDetailVO

private const val STATS_TITLE = "Stats"

@Composable
fun StatsRecyclerComponent(
    modifier: Modifier,
    stats: List<StatDetailVO>,
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
    stat: StatDetailVO,
    subTitleStyle: TextStyle,
    backGroundColor: Color?
) {
    val sliderPosition by remember { mutableIntStateOf(stat.level) }
    Column(
        modifier = modifier
    ) {
        Text(
            text = "${stat.name} $sliderPosition",
            style = subTitleStyle,
            color = stat.color.value
        )
        Slider(
            value = sliderPosition.toFloat(),
            onValueChange = { /* no-op */ },
            colors = SliderDefaults.colors(
                thumbColor = stat.color.value,
                activeTrackColor = stat.color.value,
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
        StatDetailVO(
            name = "Hp",
            color = POKEMON_STAT_COLOR.HP,
            level = 50
        ),
        StatDetailVO(
            name = "Attack",
            color = POKEMON_STAT_COLOR.ATTACK,
            level = 25
        ),
        StatDetailVO(
            name = "Speed",
            color = POKEMON_STAT_COLOR.SPEED,
            level = 98
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
