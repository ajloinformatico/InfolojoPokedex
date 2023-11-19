package es.infolojo.infolojopokedex.ui.vo

import android.health.connect.datatypes.units.Percentage
import es.infolojo.infolojopokedex.data.bussines.detail.PokemonDetailBO
import es.infolojo.infolojopokedex.data.bussines.detail.PokemonsTypeBO
import java.io.Serializable

data class PokemonDetailVO(
    val id: Long,
    val name: String,
    val images: List<String>,
    val types: List<PokemonTypeVO>,
    val abilities: List<AbilityVO>
) : Serializable

data class AbilityVO(
    val name: String,
    val percentage: Int
)

// Detail mappers
fun PokemonDetailBO.toPokemonDetailVO() = PokemonDetailVO(
    id = id,
    name = name,
    images = images,
    types = types.toVO(),
    abilities = listOf(
        AbilityVO(
            name = "Fuerza",
            percentage = 30
        )
    )

)