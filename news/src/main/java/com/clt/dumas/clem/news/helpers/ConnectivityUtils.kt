package com.clt.dumas.clem.news.helpers

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityUtils{
    object ConnectivityUtils {
        fun isConnected(context: Context): Boolean {
            val connectivityManager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null
        }
    }
}