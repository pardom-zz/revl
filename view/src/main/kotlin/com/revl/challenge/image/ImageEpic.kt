package com.revl.challenge.image

import com.revl.challenge.app.AppState
import com.revl.challenge.interactor.image.SearchImages
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import redux.api.Store
import redux.observable.Epic
import javax.inject.Inject

class ImageEpic @Inject constructor(
        private val searchImages: SearchImages) : Epic<AppState> {

    override fun map(actions: Observable<out Any>, store: Store<AppState>): Observable<out Any> {
        // Use Observable.merge() for more actions.
        return actions.ofType(ImageAction.SearchImages::class.java)
                .flatMap(searchImagesMapper)
    }

    private val searchImagesMapper = { action: ImageAction.SearchImages ->
        searchImages.execute(SearchImages.Request(action.query, action.count, action.offset))
                .subscribeOn(Schedulers.io())
                .map { response -> ImageAction.AddImages(response.images) }
                .toObservable()
    }

}
