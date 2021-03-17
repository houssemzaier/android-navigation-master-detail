package com.bravedroid.app.domain.usecase

import com.bravedroid.app.domain.entity.Item
import com.bravedroid.app.domain.repository.ItemRepository

class GetItemListUseCase(
    private val itemRepository: ItemRepository,
) {
    suspend operator fun invoke(): List<Item> = itemRepository.getAllItems()
}

