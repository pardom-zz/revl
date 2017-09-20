package com.revl.challenge.ui.image

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import butterknife.bindView
import com.airbnb.epoxy.EpoxyModel
import com.revl.challenge.App
import com.revl.challenge.R
import com.revl.challenge.model.Image

class ImageGridListItemView : RelativeLayout {

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

    private fun bind(image: Image, position: Int, clickListener: ((Int) -> Any?)?) {

        App.component()
                .picasso()
                .load(image.thumbnailUrl)
                .placeholder(ColorDrawable(image.accentColor))
                .into(imageView)

        setOnClickListener {
            clickListener?.invoke(position)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Model

    class Model(
            private val image: Image,
            private val position: Int,
            private val clickListener: ((Int) -> Any?)?) : EpoxyModel<ImageGridListItemView>() {

        override fun getDefaultLayout() = R.layout.image_grid_list_item_view

        override fun bind(view: ImageGridListItemView) = view.bind(image, position, clickListener)

    }

}
