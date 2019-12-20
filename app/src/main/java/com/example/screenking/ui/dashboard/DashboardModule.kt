package com.example.screenking.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.screenking.di.FragmentScoped
import com.example.screenking.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DashboardModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel
}