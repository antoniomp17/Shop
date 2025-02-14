package com.mp98.shop.core.domain.usecases

import com.mp98.shop.core.domain.repositories.VerifyRepository

class InitVerifierSessionUseCase(
    private val verifyRepository: VerifyRepository
) {
    suspend operator fun invoke(): String {
        val result = verifyRepository.generatePresentationRequest()
        return result.getOrThrow()
    }
}