package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.rtest.R
import kotlinx.android.synthetic.main.fragment_tab_b.*

class BTabFragment : Fragment() {

  companion object {
    fun newInstance() = BTabFragment()
  }

  private val adapter by lazy { BTabListAdapter() }
  private lateinit var viewModel: BTabViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_tab_b, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = getViewModel()
    rv_b_tab.adapter = adapter
    observe()
  }

  private fun observe() {
    viewModel.getItems().observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
    })
  }

  private fun getViewModel(): BTabViewModel {
    return ViewModelProvider(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return BTabViewModel(viewLifecycleOwner) as T
      }
    })[BTabViewModel::class.java]
  }
}
