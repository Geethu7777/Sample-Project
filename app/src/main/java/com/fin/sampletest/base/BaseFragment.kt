package com.fin.sampletest.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<out V : ViewDataBinding, out T : BaseViewModel> : Fragment() {

    private lateinit var mDataBinding: ViewDataBinding
    private lateinit var mViewModel: BaseViewModel
    private var progressDialogHelper: ProgressDialogHelper? = null

    abstract fun getViewModel(): BaseViewModel

    abstract fun getDataBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding

    abstract fun setActionBarTitle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = getDataBinding(inflater, container)
        progressDialogHelper = context?.let { ProgressDialogHelper(it) }
        setActionBarTitle()
        return mDataBinding.root
    }

}