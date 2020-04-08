package io.github.ovso.rtest.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.rtest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    vp_main.adapter = MainPagerAdapter(
      resources.getStringArray(R.array.tab_names),
      supportFragmentManager
    )
    tl_main.setupWithViewPager(vp_main)
  }
}
