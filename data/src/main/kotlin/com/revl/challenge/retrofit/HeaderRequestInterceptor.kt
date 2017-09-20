package com.revl.challenge.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HeaderRequestInterceptor(
        private val name: String,
        private val value: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val headerRequest = chain.request()
                .newBuilder()
                .addHeader(name, value)
                .build()

        return chain.proceed(headerRequest)
    }

}
