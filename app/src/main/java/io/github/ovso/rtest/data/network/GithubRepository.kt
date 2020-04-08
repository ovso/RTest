package io.github.ovso.rtest.data.network

class GithubRepository(baseUrl: String) : BaseRepository<GithubService>(
  baseUrl = baseUrl,
  cls = GithubService::class.java
)
