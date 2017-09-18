package com.revl.challenge.foo

import com.revl.challenge.model.Foo

data class FooState(
        val fooMap: Map<String, Foo> = emptyMap()
)
