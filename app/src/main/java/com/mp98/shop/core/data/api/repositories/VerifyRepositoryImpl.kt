package com.mp98.shop.core.data.api.repositories

import android.net.Uri
import android.util.Log
import com.mp98.shop.core.data.api.service.VerifyService
import com.mp98.shop.core.data.api.service.createPresentationRequest
import com.mp98.shop.core.domain.repositories.VerifyRepository
import kotlinx.coroutines.delay
import org.json.JSONObject
import javax.inject.Inject

class VerifyRepositoryImpl @Inject constructor(
    private val verifyService: VerifyService
) : VerifyRepository {
    override suspend fun generatePresentationRequest(): Result<Pair<String, String?>> {
        try {
            val request = createPresentationRequest()

            val response = verifyService.initOidcPresentationSession(request = request)

            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val uri = Uri.parse(responseBody.string())
                    val state = uri.getQueryParameter("state")
                    return Result.success(Pair(uri.toString(), state))
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

    override suspend fun listenToSession(id: String): Result<List<JSONObject>> {
        val maxRetries = 20
        val delayMillis = 5000L

        repeat(maxRetries) { attempt ->
                val response = verifyService.getSessionStatus(id)

                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        val jsonObject = JSONObject(responseBody.string())

                        val credentialSubject = extractCredentialsSubjects(jsonObject.toString())

                        if (credentialSubject.isNotEmpty()) {
                            return Result.success(credentialSubject)
                        }
                    }
                } else {
                    return Result.failure(Exception("Error en la solicitud: ${response.code()}"))
                }
            delay(delayMillis)
        }

        return Result.failure(Exception("Tiempo de espera agotado"))
    }

    private fun extractCredentialsSubjects(json: String): List<JSONObject> {
        // Parseamos el JSON completo
        val jsonObject = JSONObject(json)

        // Accedemos a los 'policyResults'
        val policyResults = jsonObject.optJSONObject("policyResults")?.optJSONArray("results")

        val credentialSubjects = mutableListOf<JSONObject>()

        if (policyResults != null) {
            for (i in 0 until policyResults.length()) {
                val result = policyResults.getJSONObject(i)

                // Verificamos si el 'credential' es de tipo 'DocumentId'
                if (result.getString("credential") == "ContactCredential" ||
                    result.getString("credential") == "BankCredential" ||
                    result.getString("credential") == "DocumentId") {
                    val policyResult = result.getJSONArray("policyResults").getJSONObject(0)

                    // Accedemos al campo 'credentialSubject' dentro de 'result'
                    val credentialSubject = policyResult.getJSONObject("result")
                        .getJSONObject("vc")
                        .getJSONObject("credentialSubject")

                    credentialSubjects.add(credentialSubject)
                }
            }
        }
        return credentialSubjects
    }
}