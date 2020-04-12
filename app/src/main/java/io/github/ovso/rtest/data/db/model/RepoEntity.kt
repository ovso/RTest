package io.github.ovso.rtest.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
  @PrimaryKey(autoGenerate = true) val db_id: Int = 0,
  val id: Int,
  val name: String,
  val description: String?,
  val stargazers_count: Int,
  @Embedded val owner: OwnerEntity?
)

data class OwnerEntity(
  val avatar_url: String
)
