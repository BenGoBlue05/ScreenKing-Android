package com.example.screenking.di

import com.example.screenking.SKApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.android.AndroidInjectionModule

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<SKApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: SKApplication): AppComponent
    }
}