package su.pank.ecards.data.models

import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.serialization.Serializable

@Serializable
data class UserWithSession(val session: UserSession)
