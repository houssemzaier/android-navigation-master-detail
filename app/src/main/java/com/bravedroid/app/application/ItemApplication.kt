package com.bravedroid.app.application

import android.app.Application
import android.content.Context
import com.bravedroid.app.di.RepositoryModule
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ItemApplication : Application() {

    private lateinit var appContext: Context

    private val appCoroutineContext: CoroutineContext by lazy {
        SupervisorJob() + CoroutineName("appCoroutineContext") + Dispatchers.IO
    }

    val repositoryModule: RepositoryModule by lazy { RepositoryModule(appCoroutineContext) }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}
