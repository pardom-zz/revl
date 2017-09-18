package com.revl.challenge.app

import com.revl.challenge.foo.FooEpic
import io.reactivex.Observable
import redux.api.Store
import redux.observable.Epic
import javax.inject.Inject

class AppEpic @Inject constructor(
        private val fooEpic: FooEpic) : Epic<AppState> {

    override fun map(actions: Observable<out Any>, store: Store<AppState>): Observable<out Any> {
        return Observable.merge(
                fooEpic.map(actions, store),
                Observable.empty<AppState>()
        )
    }
}
