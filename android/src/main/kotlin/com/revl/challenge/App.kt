package com.revl.challenge

import android.app.Application
import com.revl.challenge.app.AppReducer
import com.revl.challenge.app.AppState
import com.revl.challenge.di.component.AppComponent
import com.revl.challenge.di.component.DaggerAppComponent
import com.revl.challenge.di.module.AppModule
import redux.applyMiddleware
import redux.asObservable
import redux.createStore
import redux.observable.createEpicMiddleware

class App : Application() {

    init {
        Companion.instance = this
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    private val store by lazy {
        createStore(
                AppReducer,
                AppState(),
                applyMiddleware(
                        createEpicMiddleware(appComponent.appEpic())
                )
        )
    }

    companion object {

        private lateinit var instance: App

        fun component() = instance.appComponent

        fun store() = instance.store

        fun dispatch(action: Any) = store().dispatch(action)

        fun stateChanges() = store()
                .asObservable()
                .publish()
                .autoConnect()

    }

}
