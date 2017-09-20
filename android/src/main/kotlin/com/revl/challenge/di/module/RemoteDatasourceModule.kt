package com.revl.challenge.di.module

import com.revl.challenge.datasource.image.ImageRemoteDatasource
import com.revl.challenge.retrofit.image.ImageDatasource
import com.revl.challenge.retrofit.image.ImageService
import dagger.Module
import dagger.Provides

@Module
class RemoteDatasourceModule {

    @Provides
    fun imageRemoteDatasource(service: ImageService): ImageRemoteDatasource {
        return ImageDatasource(service)
    }

}
