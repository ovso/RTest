package io.github.ovso.rtest.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.model.ShareModel
import io.github.ovso.rtest.utils.rx.RxBus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    supportActionBar?.hide()
    setupViewPager()
  }

  private fun setupViewPager() {
    with(vp_main) {
      adapter = MainPagerAdapter(
        resources.getStringArray(R.array.tab_names),
        supportFragmentManager
      )
      addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
          super.onPageSelected(position)
          if (position == 1) RxBus.send(ShareModel.LoadInitial())
        }
      })
      tl_main.setupWithViewPager(this)

    }
  }
}
