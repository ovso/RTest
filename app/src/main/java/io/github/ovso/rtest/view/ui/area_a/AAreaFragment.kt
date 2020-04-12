package io.github.ovso.rtest.view.ui.area_a

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.databinding.FragmentAreaABinding
import io.github.ovso.rtest.view.ui.main.MainActivity

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

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    observe()
  }

  private fun observe() {
    viewModel.getNavigateToNewScreen().observe(viewLifecycleOwner, Observer {
      User.name = it
      startActivity(Intent(context, MainActivity::class.java))
      requireActivity().finish()
    })
    viewModel.getMessage().observe(viewLifecycleOwner, Observer {
      val makeText = Toast.makeText(context, it, Toast.LENGTH_SHORT)
      makeText.setGravity(Gravity.TOP, 0, 0)
      makeText.show()
    })
  }
}
