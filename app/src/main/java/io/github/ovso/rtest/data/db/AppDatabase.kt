package io.github.ovso.rtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ovso.rtest.data.db.model.RepoEntity
import io.github.ovso.rtest.data.db.model.RepoDao
import io.github.ovso.rtest.data.db.model.StargazerDao
import io.github.ovso.rtest.data.db.model.StargazerEntity

@Database(entities = [RepoEntity::class, StargazerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun repos(): RepoDao
  abstract fun stargazers(): StargazerDao
}
