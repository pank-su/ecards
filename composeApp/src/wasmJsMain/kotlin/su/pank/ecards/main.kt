package su.pank.ecards

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams
import su.pank.ecards.data.models.UserData

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val query = window.location.search

    val params = URLSearchParams(query.toJsString())

    val userData = runCatching { params.toUserData() }.getOrNull()

    ComposeViewport(document.body!!) {
        App(userData)
    }
}

private fun URLSearchParams.toUserData(): UserData {
    return UserData(
        get("hash").toString(),
        get("auth_date")!!.toLong(),
        get("photo_url").toString(),
        get("username").toString(),
        get("id")!!.toLong(),
        get("first_name").toString(),
        get("last_name").toString()
    )
}
