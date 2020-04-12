package io.github.ovso.rtest.view.ui.area_a

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.view.base.DisposableViewModel
import timber.log.Timber

class AAreaViewModel : DisposableViewModel() {

  private val navigateToNewScreen = MutableLiveData<String>()

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
    Timber.d("onClick()")
    navigateToNewScreen.value = name.get()
  }

  fun getNavigateToNewScreen(): LiveData<String> = navigateToNewScreen

  data class AAreaModel(
    val avatarUrl: String,
    val name: String
  )
}
