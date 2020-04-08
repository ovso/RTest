package io.github.ovso.rtest.view.ui.main

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.ovso.rtest.view.ui.area_b.tab_a.ATabFragment
import io.github.ovso.rtest.view.ui.area_b.tab_b.BTabFragment

class MainPagerAdapter(private val titles: Array<String>, fm: FragmentManager) :
  FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  override fun getItem(position: Int) = when (position == 0) {
    true -> ATabFragment.newInstance()
    false -> BTabFragment.newInstance()
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return titles[position]
  }

  override fun getCount() = titles.size
}
