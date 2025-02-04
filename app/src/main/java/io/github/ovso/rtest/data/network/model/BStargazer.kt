package io.github.ovso.rtest.data.network.model

import com.google.gson.annotations.SerializedName

data class BStargazer(
  @SerializedName("avatar_url")
  val avatarUrl: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("login")
  val login: String,
  val repoName: String,
  val repoAvatarUrl: String
)
