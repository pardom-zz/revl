package com.revl.challenge.retrofit.image

import com.revl.challenge.datasource.image.ImageRemoteDatasource
import com.revl.challenge.model.Image
import io.reactivex.Single
import javax.inject.Inject
import javax.naming.OperationNotSupportedException

class ImageDatasource @Inject constructor(
        private val service: ImageService) : ImageRemoteDatasource {

    override fun searchImages(query: String, count: Int, offset: Int): Single<List<Image>> {
        val params = mapOf(
                ImageService.PARAM_QUERY to query,
                ImageService.PARAM_COUNT to "$count",
                ImageService.PARAM_OFFSET to "$offset"
        )
        return service.searchImages(params)
                .map { it.value }
    }

    override fun get(key: String): Single<Image> {
        throw OperationNotSupportedException("Image requests are not supported at this time.")
    }

    override fun put(key: String, value: Image): Single<Image> {
        throw OperationNotSupportedException("Cannot add images to Bing image search.")
    }

    override fun remove(key: String): Single<Image> {
        throw OperationNotSupportedException("Cannot remove images from Bing image search.")
    }

}
