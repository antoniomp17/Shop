package com.mp98.shop.core.domain.repositories

interface VerifyRepository {
    suspend fun generatePresentationRequest(): Result<String>
}