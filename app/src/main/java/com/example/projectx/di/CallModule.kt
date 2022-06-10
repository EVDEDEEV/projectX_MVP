package com.example.projectx.di

import com.example.projectx.data.CallRepositoryImpl
import com.example.projectx.data.CallResolverImpl
import com.example.projectx.domain.repository.CallRepository
import com.example.projectx.domain.repository.CallResolver
import com.example.projectx.domain.useCases.CallInteractor
import com.example.projectx.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val callModule = module {
    factory<CallResolver> { CallResolverImpl(androidApplication()) }
    factory<CallRepository> { CallRepositoryImpl(get()) }
    factory { CallInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
}