package com.revl.challenge.ui.image

import com.airbnb.epoxy.EpoxyController
import com.revl.challenge.model.Image

class ImageController(
        private val detail: Boolean,
        private val clickListener: ((Int) -> Any?)? = null,
        private val longClickListener: ((Image) -> Any?)?) : EpoxyController() {

    private val _images = mutableListOf<Image>()

    fun set(images: Collection<Image>) {
        _images.clear()
        _images.addAll(images)
        requestModelBuild()
    }

    override fun buildModels() {
        _images
                .forEachIndexed { index, image ->
                    val model = if (detail) {
                        ImageDetailListItemView.Model(image, longClickListener)
                    } else {
                        ImageGridListItemView.Model(image, index, clickListener, longClickListener)
                    }
                    model.id(image.id).apply { add(this) }
                }
    }

}
