package su.pank.ecards

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform