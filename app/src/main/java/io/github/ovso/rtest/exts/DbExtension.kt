package io.github.ovso.rtest.exts

import io.github.ovso.rtest.App

fun clearDb() {
  Thread {
    App.appDb.repos().removeAll()
    App.appDb.stargazers().removeAll()
  }.start()
}
