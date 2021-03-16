package com.bravedroid.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            if (isAssignableFrom(MainActivityViewModel::class.java))
                MainActivityViewModel()
            else
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
}
