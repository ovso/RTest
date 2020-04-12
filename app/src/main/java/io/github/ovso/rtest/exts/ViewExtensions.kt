package io.github.ovso.rtest.exts

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.ovso.rtest.App

fun View.appContext(): Context {
  return context.applicationContext
}

fun ImageView.load(url: String) {
  Glide.with(this).load(url).into(this)
}
