package com.bravedroid.app.di

import com.bravedroid.app.application.ItemApplication
import com.bravedroid.app.domain.repository.ItemRepository

object DI {
    private fun injectRepositoryModule(app: ItemApplication): RepositoryModule =
        app.repositoryModule

    fun injectItemRepository(app: ItemApplication): ItemRepository =
        injectRepositoryModule(app).itemRepository

}
