package io.github.ovso.rtest.view.ui.area_a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.rtest.R
import io.github.ovso.rtest.databinding.FragmentAreaABinding

class AAreaFragment : Fragment() {

  private lateinit var viewModel: AAreaViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = getBinding(inflater, container).root

  private fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAreaABinding {
    val owner = this
    viewModel = ViewModelProvider(owner).get(AAreaViewModel::class.java)
    return DataBindingUtil.inflate<FragmentAreaABinding>(
      inflater,
      R.layout.fragment_area_a,
      container,
      false
    ).apply {
      viewModel = owner.viewModel
      lifecycleOwner = owner
    }
  }
}
