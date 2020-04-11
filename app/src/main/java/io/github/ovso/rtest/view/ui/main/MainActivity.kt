package io.github.ovso.rtest.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.github.ovso.rtest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    supportActionBar?.hide()
    vp_main.adapter = MainPagerAdapter(
      resources.getStringArray(R.array.tab_names),
      supportFragmentManager
    )
    tl_main.setupWithViewPager(vp_main)

    val a = MutableLiveData<Boolean>()
    a.observe(this, Observer {  })
  }
}
