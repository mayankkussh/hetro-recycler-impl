package com.mayankkusshl.hetrorecyclerviewwrapper

/**
 * Interface needed to be implemented by any model to be a part of Recyclerview by delegate method
 * environment
 * Created by mayankkush on 15/01/18.
 */
interface RecyclerViewListItem {
  fun getViewType(): Int
  fun getUnique(): Any
}