package com.bravedroid.app.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bravedroid.app.application.ItemApplication
import com.bravedroid.app.di.DI
import com.bravedroid.app.domain.usecase.GetItemListUseCase
import com.bravedroid.app.presentation.Util.requireItemApplication

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val app: ItemApplication) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            if (isAssignableFrom(MainActivityViewModel::class.java)) {
                val itemRepository = DI.injectItemRepository(app)
                val getItemListUseCase = GetItemListUseCase(itemRepository)
                MainActivityViewModel(getItemListUseCase)
            } else
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
}

@Suppress("UNCHECKED_CAST")
fun Fragment.viewModelFactory() = ViewModelFactory(requireItemApplication())

@Suppress("UNCHECKED_CAST")
fun AppCompatActivity.viewModelFactory() = ViewModelFactory(requireItemApplication())
