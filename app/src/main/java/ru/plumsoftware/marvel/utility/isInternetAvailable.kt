package ru.plumsoftware.marvel.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import ru.plumsoftware.marvel.application.App

fun isInternetAvailable(): Boolean {
    val result: Boolean
    val context = App.INSTANCE
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return result
}