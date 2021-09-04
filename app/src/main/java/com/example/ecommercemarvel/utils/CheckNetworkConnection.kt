package com.example.ecommercemarvel.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class CheckNetworkConnection(context: Context) {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun checkConnection(): Boolean {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}