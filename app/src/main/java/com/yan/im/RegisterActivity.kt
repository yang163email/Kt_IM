package com.yan.im

import com.yan.im.contract.RegisterContract
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 9:45
 *  @description : 注册用户界面
 */
class RegisterActivity: BaseActivity(), RegisterContract.View {
    override fun onUsernameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgressDialog(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        hideProgressDialog()
        finish()
    }

    override fun onRegisterFailed() {
        hideProgressDialog()
        toast(R.string.register_failed)
    }

    override fun getLayoutId(): Int = R.layout.activity_register
}