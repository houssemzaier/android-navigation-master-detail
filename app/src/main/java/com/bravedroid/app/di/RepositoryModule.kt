package com.bravedroid.app.di

import com.bravedroid.app.domain.repository.ItemRepository
import com.bravedroid.app.infrastructure.ItemRepositoryImpl
import kotlin.coroutines.CoroutineContext

class RepositoryModule(appCoroutineContext: CoroutineContext) {
    val itemRepository: ItemRepository by lazy {
        ItemRepositoryImpl(appCoroutineContext)
    }
}
