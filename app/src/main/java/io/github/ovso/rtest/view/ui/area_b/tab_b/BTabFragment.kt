package io.github.ovso.rtest.view.ui.area_b.tab_b

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.ovso.rtest.R

class BTabFragment : Fragment() {

  companion object {
    fun newInstance() = BTabFragment()
  }

  private lateinit var viewModel: BTabViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_tab_b, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(BTabViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
