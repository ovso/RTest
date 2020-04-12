package io.github.ovso.rtest.data.db.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StargazerDao {
  @Insert
  fun insert(stargazer: StargazerEntiry)

  @Insert
  fun insert(stargazers: List<StargazerEntiry>)

  @Query("delete from stargazers")
  fun removeAll()

  @Query("SELECT * FROM stargazers")
  fun stargazers(): LiveData<List<StargazerEntiry>>

  @Query("SELECT distinct * FROM stargazers WHERE repoName LIKE :repoName")
  fun stargazers(repoName: String): LiveData<List<StargazerEntiry>>
}
