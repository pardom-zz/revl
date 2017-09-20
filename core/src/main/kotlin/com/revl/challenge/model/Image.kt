package com.revl.challenge.model

data class Image(
        val id: String,
        val name: String?,
        val url: String,
        val thumbnailUrl: String,
        val accentColor: Int
)
