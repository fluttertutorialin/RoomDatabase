package org.multiplatform.room

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.multiplatform.room.di.initKoin

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@NotesApplication)
        }
    }
}
