package io.github.ovso.rtest.data.network.model

import android.util.SparseArray

object ShareModel {
  val stargazersSparseArray = SparseArray<List<BStargazer>>()
  val reposSparseArray = SparseArray<List<BRepo>>()

  fun addStargazers(pageKey: Int, stargazers: List<Stargazer>) {
    val bStargazers = mutableListOf<BStargazer>()
    stargazers.forEach {
      bStargazers.add(
        BStargazer(
          avatarUrl = it.avatarUrl,
          id = it.id,
          login = it.login
        )
      )
    }
    stargazersSparseArray.append(pageKey, bStargazers)
  }

  fun addRepos(pageKey: Int, repos: List<Repo>) {
    val bRepos = mutableListOf<BRepo>()
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
    reposSparseArray.append(pageKey, bRepos)
  }
}
