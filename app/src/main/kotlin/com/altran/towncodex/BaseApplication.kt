package com.altran.towncodex

import android.app.Activity
import android.app.Application

import com.altran.towncodex.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    companion object {
        private val TAG = "BaseApplication"
        lateinit var INSTANCE: BaseApplication
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    // Routing layer (VIPER)
    lateinit var cicerone: Cicerone<Router>

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().application(this).build().inject(this)
        this.initCicerone()

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .name("town-codex.realm")
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    private fun BaseApplication.initCicerone() {
        this.cicerone = Cicerone.create()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
