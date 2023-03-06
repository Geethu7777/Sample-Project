package com.fin.sampletest.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fin.sampletest.MainActivity
import com.fin.sampletest.R
import com.fin.sampletest.base.CustomToast
import com.fin.sampletest.database.AppDatabase

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 2000L
    lateinit var viewModel: SplashViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        viewModel = ViewModelProvider(
            this,
            SplashViewmodel.SplashViewmodelFactory(
                application
            )
        ).get(SplashViewmodel::class.java)
        observeData()
        fetchData()



    }

    private fun fetchData(){
        if (AppDatabase.getInstance(applicationContext)?.repoDao()!!.getAllDetails().size>0){
           setsplashscreen()
        }else{
            viewModel.doFetchRepo()
        }
    }

    private fun setsplashscreen(){
        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }, SPLASH_TIME_OUT)
    }


    private fun observeData(){
        viewModel.success.observe(this, Observer {
            it?.let {
                if (it){
                   setsplashscreen()
                }
            }
        })
    }

}
