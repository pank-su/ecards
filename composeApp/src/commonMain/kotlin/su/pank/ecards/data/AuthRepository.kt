package su.pank.ecards.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.functions.functions
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import su.pank.ecards.data.models.UserData

class AuthRepository(private val client: SupabaseClient)
{
    val sessionStatus = client.auth.sessionStatus
    /**
     * Авторизация через данные телеграмм
     */
    suspend fun authByUserData(userData: UserData){
        val resp = client.functions.invoke("verify-telegram", userData)
        if (resp.status.isSuccess()){
            client.auth.importSession(resp.body<UserSession>())
        }
    }

    suspend fun getUser(): UserInfo? {
        return client.auth.currentUserOrNull()
    }
}