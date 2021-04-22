package com.mayankkusshl.hetrorecyclerviewwrapper

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  abstract fun bind(item: RecyclerViewListItem)

}