package com.example.bobross.di

import com.example.bobross.MainActivity
import com.example.bobross.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributorModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainFragment(): MainFragment

}
