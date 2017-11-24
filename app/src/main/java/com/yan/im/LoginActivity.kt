package com.yan.im

import com.yan.im.presenter.LoginContract
import com.yan.im.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 15:50
 *  @description : 登录界面
 */
class LoginActivity: BaseActivity(), LoginContract.View {
    val mPresenter = LoginPresenter(this)

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        super.init()
        login.setOnClickListener { login() }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }

    /**
     * 登陆
     */
    private fun login() {
        val usernameStr = userName.text.toString().trim()
        val passwordStr = password.text.toString().trim()
        mPresenter.login(usernameStr, passwordStr)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        //显示正在登陆状态
        showProgressDialog(getString(R.string.logging))
    }

    override fun onLoggedInSuccess() {
        hideProgressDialog()
        //跳转主界面
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoggedInFailed() {
        hideProgressDialog()
        toast(R.string.login_failed)
    }

}