package com.riffaells.hellocodehub

import android.app.Application
import android.content.Context
import com.riffaells.hellocodehub.di.allModules
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindSingleton

open class AndroidApp : Application(), DIAware {

    override val di: DI by DI.lazy {
        bindSingleton<Context> { this@AndroidApp.applicationContext }
        bindSingleton {String()}


        import(allModules)


    }

    override fun onCreate() {
        super.onCreate()

    }



}
