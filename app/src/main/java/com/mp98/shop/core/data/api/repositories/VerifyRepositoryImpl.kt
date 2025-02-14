package com.mp98.shop.core.data.api.repositories

import android.util.Log
import com.mp98.shop.core.data.api.service.CredentialRequest
import com.mp98.shop.core.data.api.service.PresentationRequest
import com.mp98.shop.core.data.api.service.VerifyService
import com.mp98.shop.core.domain.repositories.VerifyRepository
import javax.inject.Inject

class VerifyRepositoryImpl @Inject constructor(
    private val verifyService: VerifyService
) : VerifyRepository {
    override suspend fun generatePresentationRequest(): Result<String> {
        try {
            val request = PresentationRequest(
                requestCredentials = listOf(
                    CredentialRequest(format = "jwt_vc_json", type = "DocumentId")
                )
            )

            val response = verifyService.initOidcPresentationSession(request = request)

            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    return Result.success(responseBody.string())
                } ?: run {
                    return Result.failure(Exception("Error en la solicitud: ${response.code()}"))
                }
            } else {
                return Result.failure(Exception("Error en la solicitud: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("VerifyRepository", "Error generating presentation request", e)
            return Result.failure(e)
        }
    }
}