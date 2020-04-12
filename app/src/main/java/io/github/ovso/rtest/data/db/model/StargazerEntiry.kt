package io.github.ovso.rtest.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stargazers")
data class StargazerEntiry(
  @PrimaryKey(autoGenerate = true) val db_id: Int = 0,
  val avatarUrl: String,
  val id: Int,
  val login: String,
  val repoName:String
)
