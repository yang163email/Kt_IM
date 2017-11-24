package com.yan.im.presenter

import com.yan.im.ext.isValidPassword
import com.yan.im.ext.isValidUsername

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 20:34
 *  @description : 登陆界面p层
 */
class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        //登陆前校验用户名、密码
        if (userName.isValidUsername()) {
            if (password.isValidPassword()) {
                //开始登陆
                view.onStartLogin()
                loginEaseMob()
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    /**
     * 登陆到环信服务器
     */
    private fun loginEaseMob() {

    }
}