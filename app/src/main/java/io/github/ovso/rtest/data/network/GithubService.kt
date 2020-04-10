package io.github.ovso.rtest.data.network

import io.github.ovso.rtest.data.network.model.Repo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
  @GET("/users/{user}/repos")
  fun userRepos(
    @Path("user") user: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 5
  ): Single<List<Repo>>

  @GET("/users/{user}/repos")
  fun userRepos2(
    @Path("user") user: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 5
  ): Observable<List<Repo>>

  @GET("/repos/{user}/{repo}/stargazers")
  fun stargazers(
    @Path("user") user: String,
    @Path("repo") repo: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 5
  ): Single<Any>

  @GET("/repos/{user}/{repo}/stargazers")
  fun stargazers2(
    @Path("user") user: String,
    @Path("repo") repo: String,
    @Query("page") page: Int = 1,
    @Query("per_page") per_page: Int = 5
  ): Observable<Any>
}
