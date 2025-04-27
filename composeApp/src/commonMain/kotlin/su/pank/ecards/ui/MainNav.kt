package su.pank.ecards.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import su.pank.ecards.data.models.UserData
import su.pank.ecards.ui.auth.Auth
import su.pank.ecards.ui.game.Game
import su.pank.ecards.ui.start.Start
import su.pank.ecards.ui.start.StartScreen

@Composable
fun MainNav(userData: UserData? = null) {
    val navController = rememberNavController()

    NavHost(navController, Auth){
        composable<Start> {
            StartScreen()
        }
        composable<Game> {
            Game()
        }
        composable<Auth> {
            Auth(userData) {
                navController.navigate(Start)
            }
        }
    }
}