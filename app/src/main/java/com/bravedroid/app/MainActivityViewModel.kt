package com.bravedroid.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _currentSelectedItem = MutableLiveData<String>()
    val currentSelectedItem: LiveData<String> = _currentSelectedItem

    fun currentSelectedItem(selectedItem: String) {
        _currentSelectedItem.value = selectedItem
    }
}
