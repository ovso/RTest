package io.github.ovso.rtest.exts

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
  add(subscribe)
}
