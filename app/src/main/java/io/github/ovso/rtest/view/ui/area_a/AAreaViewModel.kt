package io.github.ovso.rtest.view.ui.area_a

import androidx.databinding.ObservableField
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.view.base.DisposableViewModel

class AAreaViewModel : DisposableViewModel() {
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

  data class AAreaModel(
    val avatarUrl: String,
    val name: String
  )
}
