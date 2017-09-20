package com.revl.challenge.widget.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.Disposables

open class EndlessScrollObservable constructor(
        private val recyclerView: RecyclerView,
        private val layoutManager: LinearLayoutManager) {

    var hasMore = true
    var isLoading = true

    fun observeLoadingMore(): Observable<Boolean> {
        return Observable.create { disposable ->

            val listener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!disposable.isDisposed) {
                        val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                        val totalItemCount = layoutManager.itemCount
                        if (hasMore && !isLoading && totalItemCount - lastVisibleItem <= THRESHOLD) {
                            disposable.onNext(true)
                        }
                    }
                }
            }

            recyclerView.addOnScrollListener(listener)

            disposable.setDisposable(Disposables.fromAction {
                recyclerView.removeOnScrollListener(listener)
            })
        }
    }

    companion object {
        private val THRESHOLD = 10
    }
}
