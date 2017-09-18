package com.revl.challenge.ui

import android.content.Context
import android.util.AttributeSet
import com.revl.challenge.R
import com.revl.challenge.navigator.SceneNavigator

class RevlNavigator : SceneNavigator {

    override val defaultRoute = SceneRoute(R.layout.foo_list_view)

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0,
            defStyleRes: Int = 0
    ) : super(context, attrs, defStyleAttr, defStyleRes)

}
