package com.revl.challenge.di.module

import com.revl.challenge.datasource.foo.FooRemoteDatasource
import com.revl.challenge.retrofit.foo.FooDatasource
import com.revl.challenge.retrofit.foo.FooService
import dagger.Module
import dagger.Provides

@Module
class RemoteDatasourceModule {

    @Provides
    fun fooRemoteDatasource(service: FooService): FooRemoteDatasource {
        return FooDatasource(service)
    }

}
