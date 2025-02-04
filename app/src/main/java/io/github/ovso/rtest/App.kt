package io.github.ovso.rtest

import android.app.Application
import androidx.room.Room
import io.github.ovso.rtest.data.db.AppDatabase
import io.github.ovso.rtest.exts.clearDb
import timber.log.Timber

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    appDb = Room.databaseBuilder(this, AppDatabase::class.java, "github-repo").build()
    clearDb()
  }

  companion object {
    lateinit var appDb: AppDatabase
  }
}
