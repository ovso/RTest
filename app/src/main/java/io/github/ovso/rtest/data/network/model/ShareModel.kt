package io.github.ovso.rtest.data.network.model

import androidx.annotation.WorkerThread
import io.github.ovso.rtest.App
import io.github.ovso.rtest.data.db.model.OwnerEntity
import io.github.ovso.rtest.data.db.model.RepoEntity
import io.github.ovso.rtest.data.db.model.StargazerEntiry

object ShareModel {

  @WorkerThread
  fun addStargazers(stargazers: List<Stargazer>, repoName: String) {
    val repoEntities = mutableListOf<StargazerEntiry>()
    stargazers.forEach {
      val stargazerEntity = StargazerEntiry(
        avatarUrl = it.avatarUrl,
        id = it.id,
        login = it.login,
        repoName = repoName
      )
      repoEntities.add(stargazerEntity)
    }
    App.appDb.stargazers().insert(repoEntities)
  }

  @WorkerThread
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
}
