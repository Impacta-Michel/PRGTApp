package br.com.prgtapp

import android.app.Application
import java.lang.IllegalStateException

class PRGTApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // SingLeton
        private var appInstance: Application? = null
        fun getInstance(): Application {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}