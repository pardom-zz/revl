package com.revl.challenge.image

import com.revl.challenge.app.AppState
import com.revl.challenge.interactor.image.SearchImages
import io.reactivex.Observable
import redux.api.Store
import redux.observable.Epic
import javax.inject.Inject

class ImageEpic @Inject constructor(
        private val searchImages: SearchImages) : Epic<AppState> {

    override fun map(actions: Observable<out Any>, store: Store<AppState>): Observable<out Any> {
        val searchImagesActions = actions.ofType(ImageAction.SearchImages::class.java)
                .map(searchImagesMapper)

        // Use Observable.merge() for more actions.
        return searchImagesActions
    }

    private val searchImagesMapper = { action: ImageAction.SearchImages ->
        searchImages.execute(SearchImages.Request(action.query, action.count, action.offset))
                .toObservable()
    }

}
