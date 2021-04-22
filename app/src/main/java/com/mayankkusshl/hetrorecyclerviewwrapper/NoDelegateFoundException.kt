package com.mayankkusshl.hetrorecyclerviewwrapper

class NoDelegateFoundException(private val item: Int, private val errorClass: String) : Exception() {
    override val message: String?
        get() = "No delegate found for the view type : $item in $errorClass"
}