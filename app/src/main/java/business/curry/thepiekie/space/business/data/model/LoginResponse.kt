package business.curry.thepiekie.space.business.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("access_token_expires") val accessTokenExpires: String,
    @SerializedName("refresh_token_expires") val refreshTokenExpires: String,
    @SerializedName("user") val user: User
)
