package com.example.screenking.di

import com.example.screenking.MainActivity
import com.example.screenking.ui.dashboard.DashboardModule
import com.example.screenking.ui.home.HomeModule
import com.example.screenking.ui.moviedetails.MovieDetailsModule
import com.example.screenking.ui.notifications.NotificationsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            DashboardModule::class,
            HomeModule::class,
            NotificationsModule::class,
            MovieDetailsModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity


}
