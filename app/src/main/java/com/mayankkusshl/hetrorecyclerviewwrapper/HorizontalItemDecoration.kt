package com.mayankkusshl.hetrorecyclerviewwrapper

import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration : RecyclerView.ItemDecoration {

    private var horizontalSpacing: Int = 0

    private var start: Int = 0
    private var middle: Int = 0
    private var end: Int = 0
    private var top: Int = 0

    private var type: Int = 0

    constructor(horizontalSpacing: Int) {
        this.horizontalSpacing = horizontalSpacing
        type = 0
    }

    constructor(start: Int, middle: Int, end: Int, top: Int? = 0) {
        this.start = start
        this.end = end
        this.middle = middle
        if (top != null && top > 0)
            this.top = top

        type = 1
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        if (type == 0) {
            if (position != state.itemCount - 1) {
                outRect.right = horizontalSpacing
            }
        } else if (type == 1) {
            //First Item
            if (position == 0) {
                outRect.left = start
                if (state.itemCount == 1) {
                    //Single Item
                    outRect.right = end
                } else {
                    outRect.right = middle
                }
            } else if (position == state.itemCount - 1) {
                //Last Item
                outRect.right = end
            } else {
                outRect.right = middle
            }
            outRect.top = this.top
        }
    }
}
