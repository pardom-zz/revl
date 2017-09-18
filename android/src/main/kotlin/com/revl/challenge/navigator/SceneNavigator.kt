package com.revl.challenge.navigator

import android.content.Context
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.util.AttributeSet

abstract class SceneNavigator : Navigator.View {

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0,
            defStyleRes: Int = 0
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onPush(route: Route) {
        if (route is SceneRoute) {
            val scene = Scene.getSceneForLayout(this, route.layoutResId, context)
            val transition = ChangeBounds()
            TransitionManager.go(scene, transition)
        }
    }

    override fun onPop(route: Route) {
        onPush(route)
    }

    data class SceneRoute(
            val layoutResId: Int
    ) : Route

}
