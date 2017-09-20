package com.revl.challenge.image

import redux.api.Reducer

object ImageReducer : Reducer<ImageState> {
    override fun reduce(state: ImageState, action: Any): ImageState {
        return when (action) {
            else -> state
        }
    }
}
