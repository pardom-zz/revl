package com.revl.challenge.foo

import redux.api.Reducer

object FooReducer : Reducer<FooState> {
    override fun reduce(state: FooState, action: Any): FooState {
        return when (action) {
            else -> state
        }
    }
}
