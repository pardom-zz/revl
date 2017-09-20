package com.revl.challenge.app

import com.revl.challenge.image.ImageEpic
import io.reactivex.Observable
import redux.api.Store
import redux.observable.Epic
import javax.inject.Inject

class AppEpic @Inject constructor(
        private val imageEpic: ImageEpic) : Epic<AppState> {

    override fun map(actions: Observable<out Any>, store: Store<AppState>): Observable<out Any> {
        return Observable.merge(
                imageEpic.map(actions, store),
                Observable.empty<AppState>()
        )
    }
}
