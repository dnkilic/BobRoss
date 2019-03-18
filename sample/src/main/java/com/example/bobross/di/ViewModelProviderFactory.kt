package com.example.bobross.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

/**
 * A factory class to provide a certain view model.
 * Used with [ViewModel] has a constructor
 */
class ViewModelProviderFactory(private val viewModel: ViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

    fun <T : ViewModel> get(fragment: Fragment, modelClass: KClass<T>): T {
        return ViewModelProviders.of(fragment, this).get(modelClass.java)
    }
}
