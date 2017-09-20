package com.revl.challenge.ui.image

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import butterknife.bindView
import com.airbnb.epoxy.EpoxyModel
import com.revl.challenge.R
import com.revl.challenge.App
import com.revl.challenge.model.Image

class ImageListItemView : RelativeLayout {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private members

    // Views
    private val imageView: ImageView by bindView(R.id.image_view)

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
    // API

    private fun bind(image: Image) {
        App
                .component()
                .picasso()
                .load(image.thumbnailUrl)
                .placeholder(ColorDrawable(image.accentColor))
                .into(imageView)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Model

    class Model(private val image: Image) : EpoxyModel<ImageListItemView>() {

        override fun getDefaultLayout() = R.layout.image_list_item_view

        override fun bind(view: ImageListItemView) = view.bind(image)

    }

}
