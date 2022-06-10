package com.example.projectx.domain.useCases

import com.example.projectx.domain.repository.CallRepository
import com.example.projectx.domain.repository.CallResolver

class CallInteractor(
    private val callResolver: CallResolver,
    private val callRepository: CallRepository,
) {

    suspend fun sendToApi(): Result<Unit> {
        return try {
            // imitation of long work
//            delay(4000)

            val callModels = callResolver.getCalls()
            callRepository.insert(callModels)

            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(Exception("error"))
        }
    }

}