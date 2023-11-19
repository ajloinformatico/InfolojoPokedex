package es.infolojo.infolojopokedex.ui.vo

import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.detail.PokemonStatBO
import es.infolojo.infolojopokedex.data.common.POKEMON_STAT_COLOR
import es.infolojo.infolojopokedex.data.mappers.toSTATType
import es.infolojo.infolojopokedex.utils.formatSpacesAndCapitalize
import java.io.Serializable

data class PokemonDetailVO(
    val id: Long,
    val name: String,
    val images: List<String>,
    val types: List<PokemonTypeVO>,
    val stats: List<StatDetailVO>
) : Serializable

data class StatDetailVO(
    val name: String,
    val level: Int,
    val color: POKEMON_STAT_COLOR
)

// Detail mappers
fun PokemonDetailBO.toPokemonDetailVO() = PokemonDetailVO(
    id = id,
    name = name,
    images = images,
    types = types.toVO(),
    stats = stats.toVO(),
)

fun List<PokemonStatBO>.toVO(): List<StatDetailVO> = map {
    val color: POKEMON_STAT_COLOR = it.statName.toSTATType()
    StatDetailVO(
        name = it.statName.formatSpacesAndCapitalize(),
        level = it.statLevel,
        color = color
    )
}
