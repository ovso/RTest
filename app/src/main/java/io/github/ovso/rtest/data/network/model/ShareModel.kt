package io.github.ovso.rtest.data.network.model

object ShareModel {

  val bStargazers = mutableListOf<BStargazer>()
  val bRepos = mutableListOf<BRepo>()

  fun addStargazers(stargazers: List<Stargazer>) {
    Thread {
      stargazers.forEach {
        bStargazers.add(
          BStargazer(
            avatarUrl = it.avatarUrl,
            id = it.id,
            login = it.login
          )
        )
      }
    }.start()
  }

  fun addRepos(repos: List<Repo>) {
    Thread {
      repos.forEach {
        bRepos.add(
          BRepo(
            id = it.id,
            name = it.name,
            description = it.description,
            stargazers_count = it.stargazers_count,
            owner = BOwner(it.owner.avatar_url)
          )
        )
      }
    }.start()
  }

  class LoadInitial
}
