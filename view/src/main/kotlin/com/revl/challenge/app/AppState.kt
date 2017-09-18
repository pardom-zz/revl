package com.revl.challenge.app

import com.revl.challenge.foo.FooState

data class AppState(
        val fooState: FooState = FooState()
)
