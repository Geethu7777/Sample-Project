package com.fin.sampletest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fin.sampletest.R
import com.fin.sampletest.data.StudentData
import com.fin.sampletest.databinding.RepodetailsBinding
import com.fin.sampletest.generated.callback.OnClickListener
import com.fin.sampletest.listener.OnLoadMoreListener
import com.fin.sampletest.ui.ViewHolder


class DataAdapter(students: List<String?>?, recyclerView: RecyclerView)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0

    private var studentList: List<String?>? =students

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private val visibleThreshold = 5
    private var lastVisibleItem = 0
    private  var totalItemCount:Int = 0
    private var loading = false
    private var onLoadMoreListener: OnLoadMoreListener? = null



    override fun getItemViewType(position: Int): Int {
        return if (studentList!![position] != null) VIEW_ITEM else VIEW_PROG
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        vh = if (viewType == VIEW_ITEM) {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.list_row, parent, false
            )
            StudentViewHolder(v)
        } else {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.progressbar_item, parent, false
            )
            ProgressViewHolder(v)
        }
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StudentViewHolder) {

            holder.tvName.setText(studentList?.get(position))

        } else {
            (holder as ProgressViewHolder).progressBar.setIndeterminate(true)
        }
    }

    fun setLoaded() {
        loading = false
    }

    override fun getItemCount(): Int {
        return studentList!!.size
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener?) {
        this.onLoadMoreListener = onLoadMoreListener
    }


    //
    class StudentViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvName: TextView
        var tvEmailId: TextView
        var student: StudentData? = null

        init {
            tvName = v.findViewById(R.id.tvName)
            tvEmailId = v.findViewById(R.id.tvEmailId)

        }
    }

    class ProgressViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var progressBar: ProgressBar

        init {
            progressBar = v.findViewById(R.id.progressBar1) as ProgressBar
        }
    }
}