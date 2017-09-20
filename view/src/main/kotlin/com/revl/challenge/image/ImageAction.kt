package com.revl.challenge.image

import com.revl.challenge.model.Image

sealed class ImageAction {

    data class SearchImages(
            val query: String,
            val count: Int,
            val offset: Int
    ) : ImageAction()

    class ClearImages : ImageAction()

    data class AddImages(
            val images: List<Image>
    ) : ImageAction()

}
