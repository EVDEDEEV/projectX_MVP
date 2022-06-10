package com.example.projectx.domain.repository

import com.example.projectx.domain.models.CallModel

interface CallRepository {
    suspend fun insert(callModels: List<CallModel>)
}