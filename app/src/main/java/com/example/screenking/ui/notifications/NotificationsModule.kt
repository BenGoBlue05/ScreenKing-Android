package com.example.screenking.ui.notifications

import androidx.lifecycle.ViewModel
import com.example.screenking.di.FragmentScoped
import com.example.screenking.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class NotificationsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeNotificationsFragment(): NotificationsFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(viewModel: NotificationsViewModel): ViewModel
}