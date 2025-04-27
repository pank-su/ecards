package su.pank.ecards.ui.auth

import androidx.compose.ui.platform.UriHandler
import kotlinx.browser.document
import kotlinx.browser.window

actual fun openUrl(uriHandler: UriHandler, url: String) {
    window.location.replace(url)
}