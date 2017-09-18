package com.revl.challenge.retrofit.foo

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FooService {

    @GET("foo/{id}")
    fun getFoo(
            @Path("id") id: String
    ): Single<FooResponse>

}
