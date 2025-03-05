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
    @SerializedName("format")
    val format: String = "vc+sd-jwt",
    @SerializedName("vct")
    val vct: String,
    @SerializedName("input_descriptor")
    val inputDescriptor: InputDescriptor
)

data class InputDescriptor(
    @SerializedName("id")
    val id: String,
    @SerializedName("format")
    val format: Map<String, Any>,
    @SerializedName("constraints")
    val constraints: Constraints
)

data class Constraints(
    @SerializedName("fields")
    val fields: List<Field>,
    @SerializedName("limit_disclosure")
    val limitDisclosure: String = "required"
)

data class Field(
    @SerializedName("path")
    val path: List<String>,
    @SerializedName("filter")
    val filter: Filter
)

data class Filter(
    @SerializedName("type")
    val type: String,
    @SerializedName("pattern")
    val pattern: String
)

fun createPresentationRequest(): PresentationRequest {
    return PresentationRequest(
        requestCredentials = listOf(
            CredentialRequest(
                vct = "ContactCredential",
                inputDescriptor = InputDescriptor(
                    id = "ContactCredential",
                    format = mapOf("vc+sd-jwt" to emptyMap<String, Any>()),
                    constraints = Constraints(
                        fields = listOf(
                            Field(
                                path = listOf("$.credentialSubject.Email"),
                                filter = Filter(type = "string", pattern = ".*")
                            )
                        )
                    )
                )
            ),
            CredentialRequest(
                vct = "BankCredential",
                inputDescriptor = InputDescriptor(
                    id = "BankCredential",
                    format = mapOf("vc+sd-jwt" to emptyMap<String, Any>()),
                    constraints = Constraints(
                        fields = listOf(
                            Field(
                                path = listOf("$.credentialSubject.Card Number"),
                                filter = Filter(type = "string", pattern = "\\d{16}")
                            ),
                            Field(
                                path = listOf("$.credentialSubject.Card Holder"),
                                filter = Filter(type = "string", pattern = ".*")
                            ),
                            Field(
                                path = listOf("$.credentialSubject.Card Expiration Date"),
                                filter = Filter(type = "string", pattern = "\\d{2}/\\d{2}")
                            ),
                            Field(
                                path = listOf("$.credentialSubject.Card CVV"),
                                filter = Filter(type = "string", pattern = "\\d{3}")
                            )
                        )
                    )
                )
            ),
            CredentialRequest(
                vct = "DocumentId",
                inputDescriptor = InputDescriptor(
                    id = "DocumentId",
                    format = mapOf("vc+sd-jwt" to emptyMap<String, Any>()),
                    constraints = Constraints(
                        fields = listOf(
                            Field(
                                path = listOf("$.credentialSubject.First Name"),
                                filter = Filter(type = "string", pattern = ".*")
                            ),
                            Field(
                                path = listOf("$.credentialSubject.Last Name"),
                                filter = Filter(type = "string", pattern = ".*")
                            ),
                            Field(
                                path = listOf("$.credentialSubject.Address"),
                                filter = Filter(type = "string", pattern = ".*")
                            )
                        )
                    )
                )
            )
        )
    )
}