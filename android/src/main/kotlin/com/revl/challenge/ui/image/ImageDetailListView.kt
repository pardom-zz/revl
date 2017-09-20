package com.revl.challenge.ui.image

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import butterknife.bindView
import com.revl.challenge.App
import com.revl.challenge.R
import com.revl.challenge.navigator.Route
import com.revl.challenge.navigator.SceneNavigator.SceneRoute
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ImageDetailListView : LinearLayout {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private members

    // Views
    private val imageRecyclerView: RecyclerView by bindView(R.id.image_recycler_view)

    // Values
    private val imageController = ImageController(true)
    private val disposables = CompositeDisposable()

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructors

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0,
            defStyleRes: Int = 0
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Lifecycle

    override fun onFinishInflate() {
        super.onFinishInflate()

        imageRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        imageRecyclerView.adapter = imageController.adapter

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(imageRecyclerView)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        App.stateChanges()
                .startWith(App.store().state)
                .map { appState -> appState.imageState.imageMap.values }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { images -> imageController.set(images) }
                .apply { disposables.add(this) }
    }

    override fun onDetachedFromWindow() {
        disposables.dispose()
        super.onDetachedFromWindow()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Classes

    class ImageDetailRoute(
            val position: Int
    ) : SceneRoute(R.layout.image_detail_list_view)

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Companion

    companion object {

        fun route(position: Int): Route = ImageDetailRoute(position)

    }

}
