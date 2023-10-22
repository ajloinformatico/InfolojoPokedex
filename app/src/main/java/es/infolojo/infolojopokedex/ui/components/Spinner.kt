package es.infolojo.infolojopokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.infolojo.infolojopokedex.ui.theme.InfolojoPokedexTheme

@Composable
fun Spinner(show: Boolean = true, type: SpinnerTypes = SpinnerTypes.CIRCULAR) {
    val loading by rememberSaveable { mutableStateOf(show) }
    val spinnerType by rememberSaveable { mutableStateOf(type) }

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (spinnerType) {
                SpinnerTypes.CIRCULAR -> CircularProgressIndicator()
                SpinnerTypes.LINEAR -> LinearProgressIndicator()
            }
        }
    }
}

enum class SpinnerTypes {
    CIRCULAR,
    LINEAR
}

@Preview(showBackground = true)
@Composable
fun SpinnerPreview() {
    InfolojoPokedexTheme {
        Spinner(show = true, type = SpinnerTypes.LINEAR)
    }
}
