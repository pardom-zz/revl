package com.revl.challenge.ui.image

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import kotterknife.bindView
import com.revl.challenge.App
import com.revl.challenge.R
import com.revl.challenge.image.ImageAction
import com.revl.challenge.model.Image
import com.revl.challenge.navigator.Navigator
import com.revl.challenge.navigator.Route
import com.revl.challenge.navigator.SceneNavigator.SceneRoute
import com.revl.challenge.widget.recyclerview.EndlessScrollObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ImageDetailListView : LinearLayout, Navigator.Listener {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private members

    // Views
    private val imageRecyclerView: RecyclerView by bindView(R.id.image_recycler_view)

    // Values
    private val imageController = ImageController(true, null, createLongClickListener())
    private val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val endlessScrollObservable by lazy { EndlessScrollObservable(imageRecyclerView, layoutManager) }

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

        imageRecyclerView.layoutManager = layoutManager
        imageRecyclerView.adapter = imageController.adapter

        endlessScrollObservable.hasMore = true // Assume we always have more (for challenge)
        endlessScrollObservable.observeLoadingMore()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    // TODO: THIS IS A SHORTCUT. IGNORE!!! :)
                    val offset = imageController.adapter.itemCount
                    App.dispatch(ImageAction.SearchImages("kite surfing", PAGE_SIZE, offset))
                    endlessScrollObservable.isLoading = true
                }
                .apply { disposables.add(this) }

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(imageRecyclerView)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        App.stateChanges()
                .startWith(App.store().state)
                .map { appState -> appState.imageState.imageMap.values }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { images ->
                    imageController.set(images)
                    endlessScrollObservable.isLoading = false
                }
                .apply { disposables.add(this) }
    }

    override fun onDetachedFromWindow() {
        disposables.dispose()
        super.onDetachedFromWindow()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Listeners

    override fun onPush(route: Route) {
        if (route is ImageDetailRoute) {
            post { imageRecyclerView.scrollToPosition(route.position) }
        }
    }

    override fun onPop(route: Route) {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private functions

    private fun createLongClickListener() = { image: Image ->
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(image.name, image.url)
        clipboardManager.primaryClip = clipData
        Snackbar.make(this, R.string.image_copied, Snackbar.LENGTH_SHORT).show()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Classes

    class ImageDetailRoute(
            val position: Int
    ) : SceneRoute(R.layout.image_detail_list_view)

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Companion

    companion object {

        const val PAGE_SIZE = 20

        fun route(position: Int): Route = ImageDetailRoute(position)

    }

}
