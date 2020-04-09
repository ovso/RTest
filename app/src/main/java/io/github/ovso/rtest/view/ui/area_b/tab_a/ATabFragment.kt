package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import kotlinx.android.synthetic.main.fragment_tab_a.*

class ATabFragment : Fragment() {

  companion object {
    fun newInstance() = ATabFragment()
  }

  private val adapter by lazy { ATabAdapter() }
  private lateinit var viewModel: ATabViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_tab_a, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    with(rv_a_tab) {
      addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
      adapter = this@ATabFragment.adapter
    }
    viewModel = ViewModelProvider(this).get(ATabViewModel::class.java)
    observe()
  }

  private fun observe() {
    viewModel.getItems().observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
    })
  }
}
