package com.safwa.application

import android.app.Application
import android.content.Context
import com.github.pwittchen.reactivenetwork.library.BuildConfig
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork
import com.safwa.application.utils.Logger
import dagger.hilt.android.HiltAndroidApp
import rx.schedulers.Schedulers



@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {

        instance = this
        context =applicationContext
        Logger.init(BuildConfig.DEBUG)
        listenToNetworkConnectivity()
        super.onCreate()

    }

    private fun listenToNetworkConnectivity() {

        ReactiveNetwork.observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            // anything else what you can do with RxJava
            .observeOn(Schedulers.io())
            .subscribe { isConnected: Boolean ->

                //using logger file depend on timber lib
                Logger.e(isConnected.toString())

                //using timber lib
                //Timber.tag(TAG).e("Connection to internet is $isConnected")


                //FirebaseCrashlytics.getInstance().setCustomKey("connect_to_internet", isConnected)

            }

    }

    companion object{
        lateinit var instance: MyApplication
        lateinit var context: Context
        fun getInstance(): MyApplication {
            return instance
        }

    }

}