package com.revl.challenge.interactor.foo

import com.revl.challenge.datasource.foo.FooRemoteDatasource
import com.revl.challenge.interactor.Interactor
import com.revl.challenge.interactor.foo.GetFoo.Request
import com.revl.challenge.interactor.foo.GetFoo.Response
import com.revl.challenge.model.Foo
import io.reactivex.Single
import javax.inject.Inject

/**
 * Get a [Foo] by id.
 */
class GetFoo @Inject constructor(
        private val remote: FooRemoteDatasource) : Interactor<Request, Response> {

    override fun execute(request: Request): Single<Response> {
        return remote.get(request.id)
                .map { foo -> Response(foo) }
    }

    data class Request(
            val id: String
    ) : Interactor.Request

    data class Response(
            val foo: Foo
    ) : Interactor.Response

}
