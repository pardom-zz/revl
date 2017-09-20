package com.revl.challenge.app

import com.revl.challenge.image.ImageEpic
import io.reactivex.Observable
import redux.api.Store
import redux.observable.Epic
import javax.inject.Inject

class AppEpic @Inject constructor(
        private val imageEpic: ImageEpic) : Epic<AppState> {

    override fun map(actions: Observable<out Any>, store: Store<AppState>): Observable<out Any> {
        // User Observable.merge for more epics
        return imageEpic.map(actions, store)
    }
}
