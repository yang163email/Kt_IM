package com.yan.im.utils

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 21:52
 *  @description : 常用工具类
 */
object CommonUtil {

    /**
     * 隐藏软键盘
     */
    fun hideSoftKeyboard(activity: AppCompatActivity) {
        val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus.let { v->
            v.windowToken.let {
                manager.hideSoftInputFromWindow(it, 0)
            }
        }
    }
}