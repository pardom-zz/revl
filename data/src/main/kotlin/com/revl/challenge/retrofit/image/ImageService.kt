package com.revl.challenge.retrofit.image

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Retrofit service for Bing's image API endpoint.
 */
interface ImageService {

    /**
     * Search for images by query.
     *
     * Required query params:
     *  PARAM_QUERY
     *
     * Optional query params:
     *  PARAM_COUNT
     *  PARAM_OFFSET
     *  PARAM_MARKET
     *  PARAM_SAFE_SEARCH
     *
     *  @param query Query params for image search
     *  @return Single observable image response
     */
    @GET("$PATH_IMAGES/search")
    fun searchImages(@QueryMap params: Map<String, String>): Single<ImageResponse>

    companion object {
        const val PATH_IMAGES = "images"
        const val PARAM_QUERY = "query"
        const val PARAM_COUNT = "count"
        const val PARAM_OFFSET = "offset"
        const val PARAM_MARKET = "mkt"
        const val PARAM_SAFE_SEARCH = "safeSearch"
    }

}
