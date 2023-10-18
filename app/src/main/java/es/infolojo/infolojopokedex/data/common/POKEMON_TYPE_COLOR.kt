package es.infolojo.infolojopokedex.data.common

import androidx.compose.ui.graphics.Color
import es.infolojo.infolojopokedex.ui.theme.BugTypeColor
import es.infolojo.infolojopokedex.ui.theme.DarkTypeColor
import es.infolojo.infolojopokedex.ui.theme.DragonTypeColor
import es.infolojo.infolojopokedex.ui.theme.FairyTypeColor
import es.infolojo.infolojopokedex.ui.theme.FightingTypeColor
import es.infolojo.infolojopokedex.ui.theme.FireTypeColor
import es.infolojo.infolojopokedex.ui.theme.FlyingTypeColor
import es.infolojo.infolojopokedex.ui.theme.GhostTypeColor
import es.infolojo.infolojopokedex.ui.theme.GrassTypeColor
import es.infolojo.infolojopokedex.ui.theme.GroundTypeColor
import es.infolojo.infolojopokedex.ui.theme.IceTypeColor
import es.infolojo.infolojopokedex.ui.theme.NormalTypeColor
import es.infolojo.infolojopokedex.ui.theme.PoisonTypeColor
import es.infolojo.infolojopokedex.ui.theme.PsychicTypeColor
import es.infolojo.infolojopokedex.ui.theme.SteelTypeColor
import es.infolojo.infolojopokedex.ui.theme.WaterTypeColor

enum class POKEMON_TYPE_COLOR(val colorValue: Color) {
    NORMAL(NormalTypeColor),
    FIRE(FireTypeColor),
    WATER(WaterTypeColor),
    GRASS(GrassTypeColor),
    TYPE(IceTypeColor),
    ICE(IceTypeColor),
    FIGHT(FightingTypeColor),
    POISON(PoisonTypeColor),
    GROUND(GroundTypeColor),
    FLYING(FlyingTypeColor),
    PSYCHIC(PsychicTypeColor),
    BUG(BugTypeColor),
    GHOST(GhostTypeColor),
    DRAGON(DragonTypeColor),
    DARK(DarkTypeColor),
    STEEL(SteelTypeColor),
    FAIRY(FairyTypeColor)
}