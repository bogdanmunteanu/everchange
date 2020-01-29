package ro.bogdanmunteanu.currencyconverter.data.api

import android.content.Context
import android.net.ConnectivityManager

/**
 * Helper class to determine if device is connected to the internet
 */
class NetworkInfo(private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }
}

/**
Wrapper class for offline status
 */
class OfflineException : Exception()