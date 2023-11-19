package es.infolojo.infolojopokedex.ui.components.appBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.infolojo.infolojopokedex.R
import es.infolojo.infolojopokedex.data.common.POKEMON_TYPE_COLOR
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme
import es.infolojo.infolojopokedex.utils.ThemeHelper.findCorrectTextColor
import es.infolojo.infolojopokedex.utils.ThemeHelper.getColorSecondary

// https://www.youtube.com/watch?v=dxBiEXvwSDk
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    startIcon: ImageVector,
    onIconClick: () -> Unit,
    iconsManagerVO: IconsManagerVO = IconsManagerVO(),
    pokemonNumber: String = "",
    backGround: Color? = null
) {

    val shouldShowFilter by rememberSaveable { mutableStateOf(iconsManagerVO.showFilter) }
    val shouldShowPokemonNumber by rememberSaveable { mutableStateOf(iconsManagerVO.showPokemonNumber) }
    val backGroundColor = backGround ?: getColorSecondary()
    val textColor = findCorrectTextColor(color = backGroundColor)

    TopAppBar(
        // appbar background and color
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = backGroundColor
        ),
        title = {
            Text(
                text = title,
                color = textColor
            )
        },
        navigationIcon = {
            IconButton(onClick = { onIconClick() }) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = "",
                    tint = textColor
                )
            }
        },
        actions = {
            if (shouldShowPokemonNumber && pokemonNumber.isNotEmpty()) {
                Text(
                    text = pokemonNumber,
                    color = textColor,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(end = 10.dp)
                )
            } else {
                if (shouldShowFilter) {
                    IconButton(onClick = { onIconClick() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_filter_list_24), contentDescription = null)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    InfolojoPokedexTheme {
        AppBar(
            title = "InfolojoPokedex",
            startIcon = Icons.Filled.Close,
            onIconClick = {  },
            iconsManagerVO = IconsManagerVO(
                showFilter = true
            ),
            backGround = POKEMON_TYPE_COLOR.NORMAL.colorValue
        )
    }
}
