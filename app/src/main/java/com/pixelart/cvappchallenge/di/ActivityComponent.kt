package com.pixelart.cvappchallenge.di

import com.pixelart.cvappchallenge.ui.homescreen.HomeActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    
    fun injectHomeScreen(homeActivity: HomeActivity)
}