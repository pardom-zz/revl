package com.revl.challenge.navigator

import android.content.Context
import android.util.AttributeSet
import android.view.ViewParent
import android.widget.FrameLayout
import java.util.*

/**
 * VEEEEEERY simple and naive implementation of navigation view based on concepts in Flutter's
 * Navigator widget. It does not handle view state persistence or anything beyond swapping views.
 * I'm working on a port of Flutter's Navigator to Android at the moment, but this navigation view
 * should suffice for this challenge.
 */
class Navigator {

    abstract class View : FrameLayout, Listener {

        protected abstract val defaultRoute: Route

        private val routes = Stack<Route>()

        @JvmOverloads
        constructor(
                context: Context,
                attrs: AttributeSet? = null,
                defStyleAttr: Int = 0,
                defStyleRes: Int = 0
        ) : super(context, attrs, defStyleAttr, defStyleRes)

        override fun onFinishInflate() {
            super.onFinishInflate()
            push(defaultRoute)
        }

        /**
         * Push a route onto the navigation stack
         *
         * @param route The route to be pushed.
         */
        fun push(route: Route) {
            routes.push(route)
            onPush(route)
        }

        /**
         * Pop a route from the navigation stack.
         *
         * @return Whether the pop was handled.
         */
        fun pop(): Boolean {
            if (routes.size > 1) {
                routes.pop()
                onPop(routes.peek())
                return true
            }
            return false
        }


    }

    interface Listener {

        fun onPush(route: Route)

        fun onPop(route: Route)

    }

    companion object {

        tailrec fun of(view: android.view.View): Navigator.View {
            tailrec fun of(viewParent: ViewParent): Navigator.View {
                if (viewParent is Navigator.View) {
                    return viewParent
                }
                if (viewParent !is ViewParent) {
                    throw IllegalArgumentException("No Navigator.View ancestor exists for $view.")
                }
                return of(viewParent.parent)
            }

            if (view is Navigator.View) {
                return view
            }

            return of(view.parent)
        }

    }

}
