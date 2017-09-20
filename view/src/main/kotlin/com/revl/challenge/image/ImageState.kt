package com.revl.challenge.image

import com.revl.challenge.model.Image

data class ImageState(
        val imageMap: Map<String, Image> = linkedMapOf() // Retain order until we decide how we really want to order.
)
