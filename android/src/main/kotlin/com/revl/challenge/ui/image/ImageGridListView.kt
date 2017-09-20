package com.revl.challenge.ui.image

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import butterknife.bindView
import com.revl.challenge.App
import com.revl.challenge.R
import com.revl.challenge.image.ImageAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ImageGridListView : LinearLayout {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private members

    // Views
    private val imageRecyclerView: RecyclerView by bindView(R.id.image_recycler_view)

    // Values
    private val imageController = ImageController()
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

        imageRecyclerView.layoutManager = GridLayoutManager(context, 4) // TODO: extract span count to resource
        imageRecyclerView.adapter = imageController.adapter


        // TODO: Search edit text
        App.dispatch(ImageAction.SearchImages("kite surfing", 10, 0))
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

}
