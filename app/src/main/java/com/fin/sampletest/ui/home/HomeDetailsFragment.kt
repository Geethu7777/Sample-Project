package com.fin.sampletest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fin.sampletest.adapter.RepoListAdapter
import com.fin.sampletest.base.BaseFragment
import com.fin.sampletest.base.BaseViewModel
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.databinding.FragmentHomeBinding
import com.fin.sampletest.databinding.FragmentHomedetailsBinding
import com.fin.sampletest.listener.ItemClickListener
import com.fin.sampletest.ui.splash.SplashViewmodel

class HomeDetailsFragment : Fragment()   {

    private var _binding: FragmentHomedetailsBinding? = null
    lateinit var viewModel: HomeViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

          viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomedetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        _binding?.name= requireArguments().getString("name")!!
        _binding?.desc= requireArguments().getString("desc")!!


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}