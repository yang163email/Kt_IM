package com.yan.im.contract

import com.yan.im.presenter.BasePresenter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 17:56
 *  @description : 登录界面协议层
 */
interface LoginContract {
    interface Presenter: BasePresenter {
        fun login(userName: String, password: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}