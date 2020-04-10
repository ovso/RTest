package io.github.ovso.rtest.data.network

import io.github.ovso.rtest.data.network.model.RepoResponse
import io.github.ovso.rtest.data.network.model.StargazerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
  @GET("/users/{user}/repos")
  fun userRepos(
    @Path("user") user: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 3
  ): Single<List<RepoResponse>>

  @GET("/repos/{user}/{repo}/stargazers")
  fun stargazers(
    @Path("user") user: String,
    @Path("repo") repo: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 3
  ): Single<List<StargazerResponse>>
}
