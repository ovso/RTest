package io.github.ovso.rtest.data.network.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
  @SerializedName("id")
  var id: Int,
  @SerializedName("name")
  var name: String,
  @SerializedName("description")
  var description: String,
  @SerializedName("stargazers_count")
  var stargazers_count: Int
)
