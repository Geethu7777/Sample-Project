package com.fin.sampletest.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var mUserId: Int = 0
    var isLoading = MutableLiveData<Boolean>()
    var eventFilter: String = "None"
    protected val uiScope = viewModelScope


    init {
//        eventFilter = "None"

    }


    fun getLoading(): MutableLiveData<Boolean> {
        return isLoading
    }

    var showProgress = MutableLiveData<Boolean>()

    fun setProgressStatus(): MutableLiveData<Boolean> {
        return showProgress
    }

}
