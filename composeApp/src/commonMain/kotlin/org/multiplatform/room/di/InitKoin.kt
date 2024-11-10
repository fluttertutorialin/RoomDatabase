package org.multiplatform.room.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.multiplatform.room.di.commonModule
import org.multiplatform.room.di.platformModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(commonModule, platformModule)
    }
}
