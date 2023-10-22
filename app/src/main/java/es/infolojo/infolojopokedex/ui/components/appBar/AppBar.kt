package es.infolojo.infolojopokedex.ui.components.appBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import es.infolojo.infolojopokedex.R
import es.infolojo.infolojopokedex.ui.components.Spinner
import es.infolojo.infolojopokedex.ui.components.SpinnerTypes
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, startIcon: ImageVector, onIconClick: () -> Unit, iconsManagerVO: IconsManagerVO = IconsManagerVO()) {

    val shouldShowFilter by rememberSaveable { mutableStateOf(iconsManagerVO.showFilter) }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = { onIconClick() }) {
                Icon(imageVector = startIcon , contentDescription = "")
            }
        },
        actions = {
            if (shouldShowFilter) {
                IconButton(onClick = { onIconClick() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_filter_list_24), contentDescription = null)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    InfolojoPokedexTheme {
        Spinner(show = true, type = SpinnerTypes.LINEAR)
    }
}
