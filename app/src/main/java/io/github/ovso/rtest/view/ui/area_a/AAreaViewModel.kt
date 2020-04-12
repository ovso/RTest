package io.github.ovso.rtest.view.ui.area_a

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.github.ovso.rtest.view.base.DisposableViewModel

class AAreaViewModel : DisposableViewModel() {

  private val navigateToNewScreen = MutableLiveData<String>()
  private val message = MutableLiveData<Int>()
  private val repository by lazy { GithubRepository() }
  private var userName: String? = null

  init {
    observe()
  }

  val displayAvatarUrl = ObservableField<String>()
  val displayName = ObservableField<String>()

  private fun observe() {
    compositeDisposable += RxBus.toObservable().subscribe {
      if (it is AAreaModel) {
        displayAvatarUrl.set(it.avatarUrl)
        displayName.set(it.name)
        userName = it.name
      }
    }
  }

  fun onClick() {
    userName?.let {
      reqUserRepos(it)
    } ?: run {
      message.value = R.string.empty_data
    }
  }

  private fun reqUserRepos(userName:String) {
    fun onSuccess(repos: List<Repo>) {
      if (repos.isNullOrEmpty()) {
        message.value = R.string.empty_data
      } else {
        navigateToNewScreen.value = displayName.get()
      }
    }
    repository.api().userRepos(userName, 1, 30)
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
