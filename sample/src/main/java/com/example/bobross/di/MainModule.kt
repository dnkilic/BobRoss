package com.example.bobross.di

import com.example.bobross.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides fun provideViewModelFactory(viewModel: MainViewModel): ViewModelProviderFactory {
        return ViewModelProviderFactory(viewModel)
    }
}
