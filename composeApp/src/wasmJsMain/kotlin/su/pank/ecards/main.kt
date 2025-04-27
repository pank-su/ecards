package su.pank.ecards

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val query = window.location.search

    val params = URLSearchParams(query as JsAny)

    println(params) // делаем запрос


    ComposeViewport(document.body!!) {
        App()
    }
}