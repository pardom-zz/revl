package com.revl.challenge.ui.image

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.view.clicks
import com.revl.challenge.navigator.Navigator

class ImageGridListView : LinearLayout {

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0,
            defStyleRes: Int = 0
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        clicks().subscribe {
            Navigator.of(this)
                    .push(ImageDetailListView.route())
        }

    }

}
