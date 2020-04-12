package io.github.ovso.rtest.exts

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.appContext(): Context {
  return context.applicationContext
}

fun ImageView.load(o: Any) {
  Glide.with(this).load(o).into(this)
}
