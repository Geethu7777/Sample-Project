package com.fin.sampletest.ui.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fin.sampletest.adapter.DataAdapter
import com.fin.sampletest.databinding.FragmentDashboardBinding
import com.fin.sampletest.listener.OnLoadMoreListener


class DashboardFragment : Fragment() {
    private var mAdapter: DataAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null

    private lateinit var studentList: ArrayList<String>


      var handler: Handler? = null
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        studentList = ArrayList<String>()
        loadData();
        _binding?.rvSampleGrid?.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context)

        // use a linear layout manager

        // use a linear layout manager
        _binding?.rvSampleGrid?.setLayoutManager(mLayoutManager)

        // create an Object for Adapter

        // create an Object for Adapter
        mAdapter = _binding?.rvSampleGrid?.let { DataAdapter(studentList, it) }

        // set the adapter object to the Recyclerview

        // set the adapter object to the Recyclerview
        _binding?.rvSampleGrid?.setAdapter(mAdapter)

        mAdapter!!.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
                studentList.add(null.toString())
                mAdapter!!.notifyItemInserted(studentList.size - 1)
                handler!!.postDelayed({
                    //   remove progress item
                    studentList.removeAt(studentList.size - 1)
                    mAdapter!!.notifyItemRemoved(studentList.size)
                    //add items one by one
                    val start = studentList.size
                    val end = start + 20
                    for (i in start + 1..end) {
                        studentList.add(i.toString())
                        mAdapter!!.notifyItemInserted(studentList.size)
                    }
                    mAdapter!!.setLoaded()
                    //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                }, 2000)
            }
        })

        return root
    }
      fun loadData() {
          for (i in 1..20) {


              studentList.add(i.toString())
          }

      }
    }
