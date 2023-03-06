package com.fin.sampletest.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.fin.sampletest.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.*
import java.util.concurrent.Executors

class BaseApplication : Application(), LifecycleObserver {

    private var currentActivity: Activity? = null
    private var timer: Timer = Timer()
    private val TAG: String = "BaseApplication"
    private var activity: BaseActivity<ViewDataBinding>? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        AppDatabase.getInstance(applicationContext)
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                forground = false
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(TAG, "onActivityCreated: ")
                this@BaseApplication.currentActivity = activity
            }

            override fun onActivityResumed(activity: Activity) {
                forground = true
                if (activity is BaseActivity<ViewDataBinding>) {
                    this@BaseApplication.activity = activity
                }
            }
        })



    }

    private val viewModelScope =
        CoroutineScope(Executors.newFixedThreadPool(2).asCoroutineDispatcher())

    companion object {
        var forground: Boolean = false
        lateinit var instance: BaseApplication
        val pageKeyValue = lazy {
            //     AppDatabase.getDatabase(getContext()).repoDao().getAllData().pageKeyValue
        }

        fun getContext(): Context {
            return instance.applicationContext
        }


    }



}