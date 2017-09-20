package com.revl.challenge.navigator

import android.content.Context
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater

abstract class SceneNavigator : Navigator.View, Navigator.Listener {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private members

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0,
            defStyleRes: Int = 0
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Overrides

    override fun onPush(route: Route) {
        if (route is SceneRoute) {
            val nextView = inflater.inflate(route.layoutResId, this, false)
            val scene = Scene(this, nextView)
            val transition = ChangeBounds()

            TransitionManager.go(scene, transition)

            if (nextView is Navigator.Listener) {
                nextView.onPush(route)
            }
        }
    }

    override fun onPop(route: Route) {
        onPush(route)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Classes


    open class SceneRoute(
            val layoutResId: Int
    ) : Route

}
