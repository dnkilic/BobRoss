package com.dnkilic.bobross.content

interface OnContentLoad {
    fun onSuccess(response: String)
    fun onError(throwable: Throwable)
}
