package com.yan.im.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.yan.im.contract.RegisterContract
import com.yan.im.ext.isValidPassword
import com.yan.im.ext.isValidUsername
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 10:07
 *  @description : 注册界面 P 层
 */
class RegisterPresenter(val view: RegisterContract.View): RegisterContract.Presenter {
    override fun register(username: String, password: String, confirmPassword: String) {
        //检查用户名、密码
        if (username.isValidUsername()) {
            if (password.isValidPassword()) {
                //检测两次密码是否一致
                if (password == confirmPassword) {
                    view.onStartRegister()
                    //开始注册
                    registerBmob(username, password)
                }
                else view.onConfirmPasswordError()
            } else view.onPasswordError()
        } else view.onUsernameError()
    }

    /**
     * 注册到 Bmob 服务器
     */
    private fun registerBmob(username: String, password: String) {
        val bu = BmobUser()
        bu.username = username
        bu.setPassword(password)
//        bu.email = "sendi@163.com"
        //注意：不能用save方法进行注册
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser, e: BmobException?) {
                if (e == null) {
                    //注册成功,注册到环信
                    registerEaseMob(username, password)
                } else view.onRegisterFailed()
            }
        })
    }

    private fun registerEaseMob(username: String, password: String) {
        doAsync {
            //注册失败会抛出HyphenateException
            try {
                EMClient.getInstance().createAccount(username, password) //同步方法
                //注册成功
                uiThread { view.onRegisterSuccess() }
            } catch (e: HyphenateException) {
                //注册失败
                uiThread { view.onRegisterFailed() }
            }
        }
    }
}