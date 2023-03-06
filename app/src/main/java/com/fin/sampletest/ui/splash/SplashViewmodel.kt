package com.fin.sampletest.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fin.sampletest.api.ApiFactory
import com.fin.sampletest.api.ResponseCallback
import com.fin.sampletest.base.BaseViewModel
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.database.AppDatabase
import kotlinx.coroutines.launch

class SplashViewmodel(
    application: Application
) :
    BaseViewModel(application) , ResponseCallback {
    val app = application
    private val repo =
        AppDatabase.getDao()?.let { SplashRepository(ApiFactory.makeRetrofitService(),  this, it) }
    val repoList = MutableLiveData<List<RepoResponse>>()
    val success = MutableLiveData<Boolean>()
    class SplashViewmodelFactory(
        private val app: Application
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                Application::class.java
            )
                .newInstance(app )
        }
    }
    fun doFetchRepo(){

        viewModelScope.launch {
            repo?.getRepoList()

        }
    }
    override fun onSuccess(data: Any?, from: String) {
         if (from.equals("Completed")){
             success.postValue(true)
         }
       // Log.e("Repolist."," xcxcxc " + repoList.value!!.size  )
    }

    override fun onFailure(data: Any, from: String) {
        TODO("Not yet implemented")
    }
}