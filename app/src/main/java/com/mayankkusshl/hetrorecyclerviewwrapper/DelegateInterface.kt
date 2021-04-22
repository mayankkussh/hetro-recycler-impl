package com.mayankkusshl.hetrorecyclerviewwrapper

import android.view.ViewGroup

/**
 * Common inteface must be implemented by all delegates
 * Created by mayankkush on 29/11/17.
 */
interface DelegateInterface {

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem)

}