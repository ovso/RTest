package io.github.ovso.rtest.view.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import io.github.ovso.rtest.exts.load

@BindingAdapter("img_url")
fun loadImage(v: ImageView, url: String?) {
  url?.let {
    v.load(it)
  }
}
