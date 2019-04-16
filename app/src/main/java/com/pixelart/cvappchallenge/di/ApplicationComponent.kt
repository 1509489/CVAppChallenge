package com.pixelart.cvappchallenge.di

import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent{

}