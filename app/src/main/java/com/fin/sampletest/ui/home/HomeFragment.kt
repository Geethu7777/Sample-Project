package com.fin.sampletest.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fin.sampletest.R
import com.fin.sampletest.adapter.RepoListAdapter
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.databinding.FragmentHomeBinding
import com.fin.sampletest.listener.ItemClickListener

class HomeFragment : Fragment() ,ItemClickListener<Any> {

    private var _binding: FragmentHomeBinding? = null
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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

observeData()
        viewModel.getData()
        return root
    }

    private fun observeData(){
            viewModel.repoList.observe(viewLifecycleOwner, Observer {
                it?.let {
                    _binding?.recyclerView?.layoutManager = LinearLayoutManager(activity)
                    // ArrayList of class ItemsViewModel
                    val data = it
                    // This will pass the ArrayList to our Adapter
                    val adapter = RepoListAdapter(data,this)
                    // Setting the Adapter with the recyclerview
                    _binding?.recyclerView?.adapter = adapter
                }
            })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(model: Any, key: Any) {

        var data : RepoResponse = model as RepoResponse
try {

    val bundle = Bundle()
    bundle.putString("name", data.name)
    bundle.putString("desc", data.description)
    NavHostFragment.findNavController(this).navigate(R.id.action_nav_repo_detail,bundle)
}catch (e:Exception){
    e.message?.let { Log.e("Here", it) }
}
    }
}


