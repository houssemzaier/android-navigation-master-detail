package com.bravedroid.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bravedroid.app.R
import com.bravedroid.app.domain.entity.Item
import com.bravedroid.app.domain.usecase.GetItemListUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivityViewModel(private val getItemListUseCase: GetItemListUseCase) : ViewModel() {
    private val _items = MutableLiveData<List<ItemUiModel>>()
    val items: LiveData<List<ItemUiModel>> = _items

    private val _currentSelectedItem = MutableLiveData<String>()
    val currentSelectedItem: LiveData<String> = _currentSelectedItem

    fun loadData() {
        viewModelScope.launch {
            _items.value = getItemListUseCase().map { item ->
                item.toItemUiModel()
            }
        }
    }

    fun currentSelectedItem(selectedItem: String) {
        _currentSelectedItem.value = selectedItem
    }

    private fun Item.toItemUiModel(): ItemUiModel = ItemUiModel(
        id = id,
        name = name,
        image = if (id.rem(2) == 0) R.drawable.ic_account_circle_black
        else R.drawable.ic_account_circle_outline
    )
}
