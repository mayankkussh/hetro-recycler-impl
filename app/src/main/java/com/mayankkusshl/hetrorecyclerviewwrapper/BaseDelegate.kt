package com.mayankkusshl.hetrorecyclerviewwrapper

abstract class BaseDelegate : DelegateInterface {

    override fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem) {
        holder.bind(item)
    }
}