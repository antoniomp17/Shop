package com.mp98.shop.core.data.api.service

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface VerifyService {
    @POST("openid4vc/verify")
    suspend fun initOidcPresentationSession(
        @Header("authorizeBaseUrl") authorizeBaseUrl: String = "openid4vp://authorize",
        @Header("responseMode") responseMode: String = "direct_post",
        @Header("successRedirectUri") successRedirectUri: String? = null,
        @Header("errorRedirectUri") errorRedirectUri: String? = null,
        @Header("statusCallbackUri") statusCallbackUri: String? = null,
        @Header("statusCallbackApiKey") statusCallbackApiKey: String? = null,
        @Header("stateId") stateId: String? = null,
        @Header("openId4VPProfile") openId4VPProfile: String? = null,
        @Body request: PresentationRequest
    ): Response<ResponseBody>
}

data class PresentationRequest(
    @SerializedName("request_credentials")
    val requestCredentials: List<CredentialRequest>
)

data class CredentialRequest(
    val format: String,
    val type: String
)