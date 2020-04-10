package io.github.ovso.rtest.exts

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


fun View.appContext(): Context {
  return context.applicationContext
}

fun ImageView.load(url: String) {
  Glide.with(this).load(url).into(this)
}

fun RecyclerView.vDivider() {
  addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
}

fun RecyclerView.hDivider() {
  addItemDecoration(DividerItemDecoration(context, RecyclerView.HORIZONTAL))
}
