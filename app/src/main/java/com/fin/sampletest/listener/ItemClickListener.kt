package com.fin.sampletest.listener

interface ItemClickListener <T> {
    fun onClick(model: T, key: Any)
}