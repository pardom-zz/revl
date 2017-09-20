package com.revl.challenge.app

import com.revl.challenge.image.ImageReducer
import redux.api.Reducer

object AppReducer : Reducer<AppState> {
    override fun reduce(appState: AppState, action: Any): AppState {
        return appState.copy(
                imageState = ImageReducer.reduce(appState.imageState, action)
        )
    }
}
