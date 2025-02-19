package com.mp98.shop.core.domain.usecases

import com.mp98.shop.core.domain.repositories.VerifyRepository
import org.json.JSONObject

class StartListeningSessionUseCase(
    private val verifyRepository: VerifyRepository
) {
    suspend operator fun invoke(id: String): Result<JSONObject> {
        return verifyRepository.listenToSession(id)
    }
}