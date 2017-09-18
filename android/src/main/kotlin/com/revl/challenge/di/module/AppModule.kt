package com.revl.challenge.di.module

import android.content.Context
import com.revl.challenge.RevlApplication
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(
        DataModule::class
))
class AppModule(
        private val app: RevlApplication) {

    @Provides
    fun context(): Context = app

}
