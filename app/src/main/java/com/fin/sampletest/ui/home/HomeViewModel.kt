package com.fin.sampletest.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fin.sampletest.api.ApiFactory
import com.fin.sampletest.base.BaseViewModel
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.database.AppDatabase
import com.fin.sampletest.ui.splash.SplashRepository

class HomeViewModel (
    application: Application
) : BaseViewModel(application){
    val repoList = MutableLiveData<List<RepoResponse>?>()
    private val repo =
        AppDatabase.getDao()?.let { HomeRepository(  it) }

    class HomeViewModelFactory(
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
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getData(){
        repoList.value = getRepoViewModel()
    }


    fun getRepoViewModel(): List<RepoResponse>? {
        return repo?.getRepoListRepo()
    }
}