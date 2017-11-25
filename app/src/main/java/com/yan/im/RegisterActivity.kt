package com.yan.im

import com.yan.im.contract.RegisterContract
import com.yan.im.presenter.RegisterPresenter
import com.yan.im.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 9:45
 *  @description : 注册用户界面
 */
class RegisterActivity: BaseActivity(), RegisterContract.View {
    private val mPresenter = RegisterPresenter(this)

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun init() {
        super.init()
        register.setOnClickListener { register() }
        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            register()
            true    //true表示消费时间
        }
    }

    /**
     * 注册
     */
    private fun register() {
        //先隐藏软键盘
        CommonUtil.hideSoftKeyboard(this)
        val usernameStr = userName.text.toString().trim()
        val passwordStr = password.text.toString().trim()
        val confirmPasswordStr = confirmPassword.text.toString().trim()
        mPresenter.register(usernameStr, passwordStr, confirmPasswordStr)
    }

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
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFailed() {
        hideProgressDialog()
        toast(R.string.register_failed)
    }

}