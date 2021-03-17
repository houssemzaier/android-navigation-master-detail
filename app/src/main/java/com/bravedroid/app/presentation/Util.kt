package com.bravedroid.app.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bravedroid.app.application.ItemApplication

object Util {
    fun Fragment.requireItemApplication(): ItemApplication =
        requireActivity().applicationContext as ItemApplication

    fun AppCompatActivity.requireItemApplication(): ItemApplication =
        applicationContext as ItemApplication
}
