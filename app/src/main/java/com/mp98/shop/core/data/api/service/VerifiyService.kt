package com.mp98.shop.core.data.api.service

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface VerifyService {
    @POST("openid4vc/verify")
    suspend fun initOidcPresentationSession(
        @Header("authorizeBaseUrl") authorizeBaseUrl: String = "openid4vp://app/authorize",
        @Header("responseMode") responseMode: String = "direct_post",
        @Body request: PresentationRequest
    ): Response<ResponseBody>

    @GET("openid4vc/session/{id}")
    suspend fun getSessionStatus(@Path("id") sessionId: String): Response<ResponseBody>
}

data class PresentationRequest(
    @SerializedName("request_credentials")
    val requestCredentials: List<CredentialRequest>
)

data class CredentialRequest(
    val format: String,
    val type: String
)