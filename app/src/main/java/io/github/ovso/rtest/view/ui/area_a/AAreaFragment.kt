package io.github.ovso.rtest.view.ui.area_a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.rtest.R

class AAreaFragment : Fragment() {

  companion object {
    fun newInstance() = AAreaFragment()
  }

  private lateinit var viewModel: AAreaViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_area_a, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(AAreaViewModel::class.java)
    // TODO: Use the ViewModel
  }
}
