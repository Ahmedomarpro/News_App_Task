package com.ao.anew.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ao.anew.R
import com.ao.anew.util.DataState
import com.ao.anew.util.Tools

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DataStateListener {
  //  var toolbar : Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        toolbar   = findViewById(R.id.toolbar)

        //initToolbar()
        showMainFragment()
    }
    private fun initToolbar() {
      //  toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.grey_80))
       // setSupportActionBar(toolbar)
        Tools.setSystemBarColor(this, R.color.grey_5)
        Tools.setSystemBarLight(this)
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(), "MainFragment"
            )
            .commit()
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            // Handle loading
            showProgressBar(dataState.loading)

            // Handle Message
            dataState.message?.let { event ->
                event.getContentIfNotHandled()?.let { message ->
                    showToast(message)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
           // progress_bar.visibility = View.VISIBLE
        } else {
         //   progress_bar.visibility = View.INVISIBLE
        }
    }

}
