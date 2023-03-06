package com.fin.sampletest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fin.sampletest.R
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.databinding.RepodetailsBinding
import com.fin.sampletest.listener.ItemClickListener
import com.fin.sampletest.ui.ViewHolder


class RepoListAdapter (private val mList: List<RepoResponse>, private val listener: ItemClickListener<Any>) :RecyclerView.Adapter<ViewHolder<RepodetailsBinding>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<RepodetailsBinding> {
        return ViewHolder(
            RepodetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder<RepodetailsBinding>, position: Int) {
        holder.binding.model = mList.get(position)
        holder.binding.listener = listener
    }

    override fun getItemCount() = mList.size
}