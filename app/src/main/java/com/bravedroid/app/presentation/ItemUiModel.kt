package com.bravedroid.app.presentation

import androidx.annotation.DrawableRes

data class ItemUiModel(
    val id: Int,
    val name: String,
    @DrawableRes
    val image: Int,
)
