package com.example.projectx.data

import com.example.projectx.domain.models.CallModel
import com.example.projectx.domain.repository.CallRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CallRepositoryImpl(
    private val apiInterface: ApiInterface,
) : CallRepository {

    override suspend fun insert(callModels: List<CallModel>) {
        withContext(Dispatchers.IO) {
            callModels.forEach { callModel ->
                apiInterface.insert(
                    number = callModel.number,
                    type = callModel.type,
                    duration = callModel.duration,
                    date = callModel.date
                )
            }
        }
    }

}