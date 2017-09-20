package com.revl.challenge.image

import redux.api.Reducer

object ImageReducer : Reducer<ImageState> {

    override fun reduce(state: ImageState, action: Any): ImageState {
        return when (action) {
            is ImageAction.ClearImages -> {
                state.copy(
                        imageMap = emptyMap()
                )
            }
            is ImageAction.AddImages -> {
                state.copy(
                        imageMap = state.imageMap + action.images.associateBy { it.id }
                )
            }
            else -> state
        }
    }

}
