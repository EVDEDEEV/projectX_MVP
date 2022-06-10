package com.example.projectx.domain.repository

import com.example.projectx.domain.models.CallModel

interface CallResolver {
    suspend fun getCalls(): List<CallModel>
}