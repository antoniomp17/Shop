package com.mp98.shop.core.domain.repositories

import org.json.JSONObject

interface VerifyRepository {
    suspend fun generatePresentationRequest(): Result<Pair<String, String?>>
    suspend fun listenToSession(id: String): Result<JSONObject>
}