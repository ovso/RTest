package io.github.ovso.rtest.data.network.model

import android.util.SparseArray
import java.util.concurrent.atomic.AtomicInteger

object ShareModel {
  private val stargazersPageKey = AtomicInteger(0)
  private val reposPageKey = AtomicInteger(0)
  val stargazersSparseArray = SparseArray<List<BStargazer>>()
  val reposSparseArray = SparseArray<List<BRepo>>()

  fun addStargazers(stargazers: List<Stargazer>) {
    Thread {
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
      stargazersSparseArray.append(stargazersPageKey.getAndIncrement(), bStargazers)
    }.start()
  }

  fun addRepos(repos: List<Repo>) {
    Thread {
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
      reposSparseArray.append(reposPageKey.getAndIncrement(), bRepos)
    }.start()
  }

  class LoadInitial
}
