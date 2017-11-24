package com.yan.im.contract

import android.os.Handler
import android.os.Looper

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 15:17
 *  @description : 所有presenter的基类
 */
interface BasePresenter {

    companion object {
        val handler by lazy { Handler(Looper.getMainLooper()) }
    }

    fun uiThread(f: ()->Unit) {
        handler.post(f)
    }
}