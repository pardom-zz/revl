package com.revl.challenge.ui.image

import com.airbnb.epoxy.EpoxyController
import com.revl.challenge.model.Image

class ImageController : EpoxyController() {

    private val _images = mutableListOf<Image>()

    fun set(images: Collection<Image>) {
        _images.clear()
        _images.addAll(images)
        requestModelBuild()
    }

    override fun buildModels() {
        _images
                .map { image -> ImageListItemView.Model(image).id(image.id) }
                .forEach { add(it) }
    }

}
