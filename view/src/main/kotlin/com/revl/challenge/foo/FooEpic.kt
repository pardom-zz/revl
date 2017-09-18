package com.revl.challenge.foo

import com.revl.challenge.app.AppState
import com.revl.challenge.interactor.foo.GetFoo
import io.reactivex.Observable
import redux.api.Store
import redux.observable.Epic
import javax.inject.Inject

class FooEpic @Inject constructor(
        private val getFoo: GetFoo) : Epic<AppState> {

    override fun map(actions: Observable<out Any>, store: Store<AppState>): Observable<out Any> {
        val getFooActions = actions.ofType(FooAction.GetFoo::class.java)
                .map(getFooMapper(store))

        // Use Observable.merge() for more actions.
        return getFooActions
    }

    fun getFooMapper(store: Store<AppState>) = { action: FooAction.GetFoo ->
        getFoo.execute(GetFoo.Request(action.id))
                .toObservable()
    }
}
