package com.fin.sampletest.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.fin.sampletest.base.*
import java.util.*

abstract class BaseActivity<out V : ViewDataBinding> : AppCompatActivity() {

    open fun getContentView(): Int {
        return 0
    }

    private var fragment: BaseFragment<ViewDataBinding, BaseViewModel>? = null
    private var progressDialogHelper: ProgressDialogHelper? = null

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }



    fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities: NetworkCapabilities? = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                isConnected = true
            }
        } else {
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            activeNetwork?.let {
                isConnected = it.isConnected
            }
        }
        return isConnected
    }

    fun addFragment(containerId: Int, fragment: BaseFragment<*, *>, tag: String) {
        supportFragmentManager.beginTransaction()
            ?.add(containerId, fragment, tag)
            ?.addToBackStack(tag)?.commit()
    }

    fun pushFragment(
        containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String,
        backStackStateName: String, isAnim: Boolean
    ) {

        val ft: FragmentTransaction = getSupportFragmentManager()
            .beginTransaction()

        ft.replace(containerViewId, fragment, fragmentTag)
        ft.addToBackStack(backStackStateName)
        ft.commit();
    }

    fun showToast(message: String) {
        CustomToast.successToast(this, message)
    }


    fun getFragmentManger(): FragmentManager {
        return supportFragmentManager
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    @SuppressLint("MissingPermission")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

        }
    }

}
