package com.yan.im.contract

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 15:16
 *  @description : TODO
 */
interface SplashContract {
    interface Presenter : BasePresenter {
        fun checkLoginStatus()  //检查登录状态
    }

    interface View {
        /** 没有登录ui处理 */
        fun onNotLoggedIn()
        /** 已经登录ui处理 */
        fun onLoggedIn()
    }
}