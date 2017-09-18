package com.revl.challenge.app

import com.revl.challenge.foo.FooReducer
import redux.api.Reducer

object AppReducer : Reducer<AppState> {
    override fun reduce(appState: AppState, action: Any): AppState {
        return appState.copy(
                fooState = FooReducer.reduce(appState.fooState, action)
        )
    }
}
