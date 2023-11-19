package es.infolojo.infolojopokedex.data.common

import androidx.compose.ui.graphics.Color
import es.infolojo.infolojopokedex.ui.theme.AttackStatColor
import es.infolojo.infolojopokedex.ui.theme.DefenseStatColor
import es.infolojo.infolojopokedex.ui.theme.HpStatColor
import es.infolojo.infolojopokedex.ui.theme.SpecialAttackStatColor
import es.infolojo.infolojopokedex.ui.theme.SpecialDefenseStatColor
import es.infolojo.infolojopokedex.ui.theme.SpeedStatColor

enum class POKEMON_STAT_COLOR(val value: Color) {
    HP(value = HpStatColor),
    ATTACK(value = AttackStatColor),
    SPECIAL_ATTACK(value = SpecialAttackStatColor),
    DEFENSE(value = DefenseStatColor),
    SPECIAL_DEFENSE(value = SpecialDefenseStatColor),
    SPEED(value = SpeedStatColor),
    UNKNOWN(value = Color.White)
}
