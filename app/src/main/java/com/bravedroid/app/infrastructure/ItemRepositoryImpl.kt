package com.bravedroid.app.infrastructure

import com.bravedroid.app.domain.entity.Item
import com.bravedroid.app.domain.repository.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class ItemRepositoryImpl(
    override val coroutineContext: CoroutineContext,
) : ItemRepository, CoroutineScope {
    override suspend fun getAllItems(): List<Item> {
        delay(TimeUnit.SECONDS.toMillis(2))

        return (0..50_000).toList().map {
            Item(
                id = it,
                name = "item $it",
            )
        }
    }
}
