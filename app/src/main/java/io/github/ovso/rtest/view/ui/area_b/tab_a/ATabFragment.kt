package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.rtest.R

class ATabFragment : Fragment() {

  companion object {
    fun newInstance() = ATabFragment()
  }

  private lateinit var viewModel: ATabViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_tab_a, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(ATabViewModel::class.java)
  }

}
