package com.bravedroid.app.domain.repository

import com.bravedroid.app.domain.entity.Item

interface ItemRepository {
   suspend fun getAllItems(): List<Item>
}
