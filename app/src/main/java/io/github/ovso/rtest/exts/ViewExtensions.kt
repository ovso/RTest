package io.github.ovso.rtest.exts

import android.content.Context
import android.view.View


fun View.appContext(): Context {
  return context.applicationContext
}
