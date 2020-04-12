package io.github.ovso.rtest.data.db.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReposDao {
  @Insert
  fun insert(repo: RepoEntity)

  @Query("delete from repos")
  fun removeAll()

  @Query("SELECT * FROM repos")
  fun repos(): List<RepoEntity>
}
