package io.github.ovso.rtest.data.network.model


import com.google.gson.annotations.SerializedName

data class Stargazer(
  @SerializedName("avatar_url")
  val avatarUrl: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("login")
  val login: String
)
