package com.revl.challenge.image

sealed class ImageAction {

    data class SearchImages(
            val query: String,
            val count: Int,
            val offset: Int
    ) : ImageAction()

}
