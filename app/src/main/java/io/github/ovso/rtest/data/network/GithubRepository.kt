package io.github.ovso.rtest.data.network

class GithubRepository(
  baseUrl: String = BASE_URL,
  token: String = "77d0e784a45e74f1bc84d272436035c49ca03f78"
) : BaseRepository<GithubService>(
  baseUrl = baseUrl,
  cls = GithubService::class.java,
  token = token
)
