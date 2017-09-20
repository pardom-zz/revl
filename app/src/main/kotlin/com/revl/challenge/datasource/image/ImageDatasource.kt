package com.revl.challenge.datasource.image

import com.revl.challenge.datasource.Datasource
import com.revl.challenge.model.Image
import io.reactivex.Single

interface ImageDatasource : Datasource<String, Image> {

    fun searchImages(query: String, count: Int, offset: Int): Single<List<Image>>

}
