package com.mayankkusshl.hetrorecyclerviewwrapper

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener {
    var previousTotal = 0 // The total number of items in the dataset after the last load
    var isLoading = false // True if we are still waiting for the last set of data to load.
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0
    internal var firstVisibleItemB: Int = 0
    public val visibleThreshold = 4 // The minimum amount of items to have below your current scroll position before loading more.
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mStaggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    private var callLoadMore = true

    constructor(layoutManager: RecyclerView.LayoutManager) {
        if(layoutManager is LinearLayoutManager)
            this.mLinearLayoutManager = layoutManager
        else if(layoutManager is StaggeredGridLayoutManager)
            this.mStaggeredGridLayoutManager = layoutManager
    }

    fun setmLinearLayoutManager(mLinearLayoutManager: LinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    fun refreshData() {
        this.previousTotal = 0
        this.isLoading = true
        this.callLoadMore = true
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(this.mLinearLayoutManager != null) {
            firstVisibleItemB = mLinearLayoutManager!!.findFirstVisibleItemPosition()
            val reverseLayout = mLinearLayoutManager!!.reverseLayout
            if (!reverseLayout) {
                visibleItemCount = recyclerView.childCount
                totalItemCount = mLinearLayoutManager!!.itemCount
                firstVisibleItem = mLinearLayoutManager!!.findFirstVisibleItemPosition()
                Log.d(TAG, "total - " + totalItemCount + "  prev total - " + previousTotal)
                if (callLoadMore && !isLoading) {
                    val diff = totalItemCount - visibleThreshold - visibleItemCount
                    if (firstVisibleItem >= 0 && firstVisibleItem > diff) {
                        previousTotal = totalItemCount
                        Log.d(TAG, "on load more")
                        onLoadMore()
                    }
                }
            } else {
                Log.d(TAG, "This scroll listener does not work with reverse layout.")
            }
        } else if(this.mStaggeredGridLayoutManager != null) {
            val startPositions = IntArray(2)
            val startPositionsB = IntArray(2)
            firstVisibleItemB = mStaggeredGridLayoutManager!!.findFirstVisibleItemPositions(startPositionsB)[0]
            val reverseLayout = mStaggeredGridLayoutManager!!.reverseLayout
            if (!reverseLayout) {

                visibleItemCount = recyclerView.childCount
                totalItemCount = mStaggeredGridLayoutManager!!.itemCount
                firstVisibleItem = mStaggeredGridLayoutManager!!.findFirstCompletelyVisibleItemPositions(startPositions)[0]
                Log.d(TAG, "total - " + totalItemCount + "  prev total - " + previousTotal)
                if (callLoadMore && !isLoading) {
                    val diff = totalItemCount - visibleThreshold - visibleItemCount
                    if (firstVisibleItem >= 0 && firstVisibleItem > diff) {
                        previousTotal = totalItemCount
                        Log.d(TAG, "on load more")
                        onLoadMore()
                    }
                }
            } else {
                Log.d(TAG, "This scroll listener does not work with reverse layout.")
            }
        }
    }

    abstract fun onLoadMore()

    fun setCallLoadMore(callLoadMore: Boolean) {
        this.callLoadMore = callLoadMore
    }

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}