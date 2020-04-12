package io.github.ovso.rtest.data.db.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StargazerDao {
  @Insert
  fun insert(stargazer: StargazerEntity)

  @Insert
  fun insert(stargazers: List<StargazerEntity>)

  @Query("delete from stargazers")
  fun removeAll()

  @Query("SELECT * FROM stargazers")
  fun stargazers(): LiveData<List<StargazerEntity>>

  @Query("SELECT * FROM stargazers WHERE login LIKE :login")
  fun stargazers(login: String): LiveData<List<StargazerEntity>>
}
