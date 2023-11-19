package es.infolojo.infolojopokedex.data.bussines.detail

data class PokemonDetailBO(
    val name: String,
    val id: Long,
    val images: List<String>,
    val types: List<PokemonsTypeBO>,
    val stats: List<PokemonStatBO>
)
