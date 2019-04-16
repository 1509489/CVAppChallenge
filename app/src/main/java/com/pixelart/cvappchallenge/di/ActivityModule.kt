package com.pixelart.cvappchallenge.di

import androidx.appcompat.app.AppCompatActivity
import com.pixelart.cvappchallenge.network.NetworkService
import com.pixelart.cvappchallenge.ui.homescreen.HomeActivity
import com.pixelart.cvappchallenge.ui.homescreen.HomeContract
import com.pixelart.cvappchallenge.ui.homescreen.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (private val activity: AppCompatActivity){
    
    @Provides
    @ActivityScope
    fun providesHomePresenter(networkService: NetworkService): HomeContract.Presenter = HomePresenter(activity as HomeActivity, networkService)
}