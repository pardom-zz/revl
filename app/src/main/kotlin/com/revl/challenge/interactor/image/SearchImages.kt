package com.revl.challenge.interactor.image

import com.revl.challenge.datasource.image.ImageRemoteDatasource
import com.revl.challenge.interactor.Interactor
import com.revl.challenge.interactor.image.SearchImages.Request
import com.revl.challenge.interactor.image.SearchImages.Response
import com.revl.challenge.model.Image
import io.reactivex.Single
import javax.inject.Inject

/**
 * Get a [Image] by search term.
 */
class SearchImages @Inject constructor(
        private val remote: ImageRemoteDatasource) : Interactor<Request, Response> {

    override fun execute(request: Request): Single<Response> {
        return remote.searchImages(request.query, request.count, request.offset)
                .map { values -> Response(values) }
    }

    data class Request(
            val query: String,
            val count: Int,
            val offset: Int
    ) : Interactor.Request

    data class Response(
            val image: List<Image>
    ) : Interactor.Response

}
