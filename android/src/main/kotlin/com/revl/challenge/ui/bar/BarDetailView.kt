package com.revl.challenge.ui.bar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.revl.challenge.R.layout
import com.revl.challenge.navigator.Route
import com.revl.challenge.navigator.SceneNavigator.SceneRoute

class BarDetailView : LinearLayout {

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0,
            defStyleRes: Int = 0
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    companion object {

        fun route(): Route = SceneRoute(layout.bar_detail_view)

    }

}
