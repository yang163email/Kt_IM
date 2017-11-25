package com.yan.im.contract

import com.yan.im.presenter.BasePresenter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 9:50
 *  @description : 注册界面协议
 */
interface RegisterContract {
    interface Presenter: BasePresenter {
        fun register(username: String, password: String, confirmPassword: String)
    }

    interface View {
        fun onUsernameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
    }
}