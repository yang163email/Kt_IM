package com.yan.im.presenter

import com.yan.im.contract.RegisterContract
import com.yan.im.ext.isValidPassword
import com.yan.im.ext.isValidUsername

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 10:07
 *  @description : 注册界面 P 层
 */
class RegisterPresenter(val view: RegisterContract.View): RegisterContract.Presenter {
    override fun register(username: String, password: String, confirmPassword: String) {
        //检查用户名、密码
        if (username.isValidUsername()) {
            if (password.isValidPassword() and confirmPassword.isValidPassword()) {
                //检测两次密码是否一致
                if (password == confirmPassword) view.onStartRegister()
                else view.onConfirmPasswordError()
            } else view.onPasswordError()
        } else view.onUsernameError()
    }
}