package com.revl.challenge.di.component

import com.revl.challenge.app.AppEpic
import com.revl.challenge.di.module.AppModule
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun appEpic(): AppEpic

    fun picasso(): Picasso

}
