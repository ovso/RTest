package io.github.ovso.rtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ovso.rtest.data.db.model.RepoEntity
import io.github.ovso.rtest.data.db.model.ReposDao

@Database(entities = [RepoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun repos(): ReposDao
}
