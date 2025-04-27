package su.pank.ecards.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val hash: String,
    @SerialName("auth_date") val authDate: Long,
    @SerialName("photo_url") val photoUrl: String,
    val username: String,
    val id: Long,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String
)
