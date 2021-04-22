package com.mayankkusshl.hetrorecyclerviewwrapper

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtils(
    private val oldItems: List<RecyclerViewListItem>,
    private val newItems: List<RecyclerViewListItem>
) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique()

    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique()
    }
}