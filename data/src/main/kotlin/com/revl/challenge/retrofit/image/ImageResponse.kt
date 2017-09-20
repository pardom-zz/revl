package com.revl.challenge.retrofit.image

import com.revl.challenge.model.Image as CoreImage

data class ImageResponse(
        val value: List<Image>) {

    data class Image(
            val imageId: String,
            val name: String?,
            val contentUrl: String,
            val thumbnailUrl: String,
            val accentColor: String
    )

    fun toCore(): List<CoreImage> =
            value.map { image ->
                CoreImage(
                        image.imageId,
                        image.name,
                        image.contentUrl,
                        image.thumbnailUrl,
                        image.accentColor.toInt(16) // converts hex string to int
                )
            }

}

