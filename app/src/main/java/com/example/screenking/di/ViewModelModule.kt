package com.example.screenking.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [SKViewModelFactory].
 */
@Module
@Suppress("UNUSED")
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: SKViewModelFactory):
            ViewModelProvider.Factory
}
