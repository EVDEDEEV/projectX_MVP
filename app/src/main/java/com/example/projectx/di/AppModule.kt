package com.example.projectx.di

import com.example.projectx.data.ApiInterface
import org.koin.dsl.module

val appModule = module {
    single<ApiInterface> { ApiInterface.newInstance() }
}