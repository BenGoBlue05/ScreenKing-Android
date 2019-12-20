package com.example.screenking.ui.home

import androidx.lifecycle.ViewModel
import com.example.screenking.di.FragmentScoped
import com.example.screenking.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: HomeViewModel): ViewModel
}