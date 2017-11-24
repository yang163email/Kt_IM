package com.yan.im.utils

import com.hyphenate.EMCallBack

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 21:05
 *  @description : EMCallBack的实现接口，只实现onProgress方法
 */
interface EMCallBackAdapter: EMCallBack {
    override fun onProgress(p0: Int, p1: String?) {  }
}