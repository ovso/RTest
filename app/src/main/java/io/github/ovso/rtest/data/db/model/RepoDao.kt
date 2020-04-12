package io.github.ovso.rtest.data.db.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepoDao {
  @Insert
  fun insert(repo: RepoEntity)

  @Insert
  fun insert(repos: List<RepoEntity>)

  @Query("delete from repos")
  fun removeAll()

  @Query("SELECT * FROM repos")
  fun repos(): LiveData<List<RepoEntity>>
}
