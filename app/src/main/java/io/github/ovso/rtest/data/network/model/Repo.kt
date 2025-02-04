package io.github.ovso.rtest.data.network.model

import com.google.gson.annotations.SerializedName

data class Repo(
  @SerializedName("id")
  val id: Int,
  @SerializedName("name")
  val name: String,
  @SerializedName("description")
  val description: String,
  @SerializedName("stargazers_count")
  val stargazers_count: Int,
  @SerializedName("owner")
  val owner: Owner
)

data class Owner(
  @SerializedName("avatar_url")
  val avatar_url: String
)
