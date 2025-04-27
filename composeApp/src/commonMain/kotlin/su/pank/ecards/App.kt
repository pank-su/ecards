package su.pank.ecards

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinApplication
import su.pank.ecards.data.di.dataModule
import su.pank.ecards.data.models.UserData
import su.pank.ecards.ui.MainNav
import su.pank.ecards.ui.di.uiModule
import su.pank.ecards.ui.theme.AppTheme

@Composable
@Preview
fun App(userData: UserData? = null) {
    AppTheme {
        KoinApplication({modules(uiModule, dataModule)}) {
            MainNav(userData)
        }
    }
}