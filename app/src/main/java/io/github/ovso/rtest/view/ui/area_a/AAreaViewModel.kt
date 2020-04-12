package io.github.ovso.rtest.view.ui.area_a

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.data.network.model.ShareModel
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.github.ovso.rtest.view.base.DisposableViewModel
import timber.log.Timber

class AAreaViewModel : DisposableViewModel() {

  private val navigateToNewScreen = MutableLiveData<String>()
  private val message = MutableLiveData<Int>()
  private val repository by lazy { GithubRepository() }

  init {
    observe()
  }

  val avatarUrl = ObservableField<String>()
  val name = ObservableField<String>()

  private fun observe() {
    compositeDisposable += RxBus.toObservable().subscribe {
      if (it is AAreaModel) {
        avatarUrl.set(it.avatarUrl)
        name.set(it.name)
      }
    }
  }

  fun onClick() {
    fun onSuccess(repos: List<Repo>) {
      if (repos.isNullOrEmpty()) {
        message.value = R.string.empty_data
      } else {
        navigateToNewScreen.value = name.get()
      }
    }
    repository.api().userRepos(User.name, 1, 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess) { println(it); message.value = R.string.empty_data }
  }

  fun getNavigateToNewScreen(): LiveData<String> = navigateToNewScreen
  fun getMessage(): LiveData<Int> = message

  data class AAreaModel(
    val avatarUrl: String,
    val name: String
  )
}
