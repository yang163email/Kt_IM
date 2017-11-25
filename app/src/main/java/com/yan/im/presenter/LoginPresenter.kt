package com.yan.im.presenter

import com.hyphenate.chat.EMClient
import com.yan.im.contract.LoginContract
import com.yan.im.ext.isValidPassword
import com.yan.im.ext.isValidUsername
import com.yan.im.utils.EMCallBackAdapter

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
                loginEaseMob(userName, password)
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    /**
     * 登陆到环信服务器
     */
    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName, password, object : EMCallBackAdapter{
            //此处回调是在子线程中
            override fun onSuccess() {
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onLoggedInFailed() }
            }
        })
    }
}