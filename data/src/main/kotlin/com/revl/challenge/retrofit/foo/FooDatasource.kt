package com.revl.challenge.retrofit.foo

import com.revl.challenge.datasource.foo.FooRemoteDatasource
import com.revl.challenge.model.Foo
import io.reactivex.Single
import javax.inject.Inject

class FooDatasource @Inject constructor(
        private val service: FooService) : FooRemoteDatasource {

    override fun get(key: String): Single<Foo> {
        return service.getFoo(key)
                .map { response -> response.foo }
    }

    override fun put(key: String, value: Foo): Single<Foo> {
        TODO("not implemented")
    }

    override fun remove(key: String): Single<Foo> {
        TODO("not implemented")
    }

}
