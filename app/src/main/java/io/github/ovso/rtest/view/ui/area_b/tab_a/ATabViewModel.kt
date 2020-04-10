package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.ItemModel
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ATabViewModel : ViewModel() {
  private val repository by lazy { GithubRepository() }
  private val items = MutableLiveData<List<ItemModel>>()

  init {
    reqItems()
  }

  private fun reqItems() {
    fun onSuccess(_items: List<ItemModel>) {
      items.value = _items
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    fun reqStargazers(repo: Repo): Single<ItemModel> {
      return repository.api()
        .stargazers(user = User.name, repo = repo.name, page = 1, per_page = 30)
        .map { ItemModel(repo, it) }
    }
    repository.api().userRepos(user = User.name, page = 1, per_page = 3)
      .flatMapObservable { Observable.fromIterable(it) }
      .flatMapSingle { reqStargazers(it) }
      .toList()
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)

  }

  fun getItems():LiveData<List<ItemModel>> = items
}
