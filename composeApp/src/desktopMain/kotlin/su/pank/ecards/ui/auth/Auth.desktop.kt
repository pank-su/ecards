package su.pank.ecards.ui.auth

import androidx.compose.ui.platform.UriHandler

actual fun openUrl(uriHandler: UriHandler, url: String) {
    uriHandler.openUri(url)
}