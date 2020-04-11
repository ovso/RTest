package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.utils.rx.SchedulerProvider

class ATabViewModel : ViewModel() {
  private val repository by lazy { GithubRepository() }
  private val items = MutableLiveData<List<Repo>>()

  init {
    reqItems()
  }

  private fun reqItems() {
    fun onSuccess(_items: List<Repo>) {
      items.value = _items
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repository.api().userRepos(User.name, 1, 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)

  }

  fun getItems():LiveData<List<Repo>> = items
}
