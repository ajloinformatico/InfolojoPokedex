package es.infolojo.infolojopokedex.ui.components.appBar

/** VO to manage Icons and actions. */
data class IconsManagerVO(
    val appBarImage: String? = null,
    val showFilter: Boolean = false,
    val showPokemonNumber: Boolean = false
)