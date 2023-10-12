package es.infolojo.infolojopokedex.data.bussines

data class PokemonContainerBO(
    val nextUrl: String,
    val previousUrl: String,
    val count: Int,
    val pokemons: List<PokemonBO>
)