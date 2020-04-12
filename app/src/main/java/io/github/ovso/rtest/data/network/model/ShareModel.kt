package io.github.ovso.rtest.data.network.model

import io.github.ovso.rtest.App
import io.github.ovso.rtest.data.db.model.OwnerEntity
import io.github.ovso.rtest.data.db.model.RepoEntity

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
    val repoEntities = mutableListOf<RepoEntity>()
    repos.forEach {
      val repoEntity = RepoEntity(
        id = it.id,
        name = it.name,
        description = it.description,
        stargazers_count = it.stargazers_count,
        owner = OwnerEntity(it.owner.avatar_url)
      )
      repoEntities.add(repoEntity)
    }
    App.appDb.repos().insert(repoEntities)
  }

  class LoadInitial
}
