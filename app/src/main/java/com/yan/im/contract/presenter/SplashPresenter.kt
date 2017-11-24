package com.yan.im.contract.presenter

import com.hyphenate.chat.EMClient
import com.yan.im.contract.SplashContract

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 16:10
 *  @description : 欢迎页p层
 */
class SplashPresenter(val view: SplashContract.View): SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn() =
            EMClient.getInstance().isConnected and EMClient.getInstance().isLoggedInBefore
}