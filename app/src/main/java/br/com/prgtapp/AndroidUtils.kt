@file:Suppress("DEPRECATION")

package br.com.prgtapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AndroidUtils {
    fun isInternetDisponivel(): Boolean {
        val conexao = PRGTApplication.getInstance().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager

        val redes = conexao.allNetworks
        return redes.map{conexao.getNetworkInfo(it)}.any{it?.state == NetworkInfo.State.CONNECTED}
    }
}