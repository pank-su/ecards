package su.pank.ecards

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinApplication
import su.pank.ecards.ui.MainNav
import su.pank.ecards.ui.di.uiModule
import su.pank.ecards.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        KoinApplication({modules(uiModule)}) {
            MainNav()
        }
    }
}