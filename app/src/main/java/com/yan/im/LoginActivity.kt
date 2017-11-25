package com.yan.im

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.yan.im.contract.LoginContract
import com.yan.im.presenter.LoginPresenter
import com.yan.im.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 15:50
 *  @description : 登录界面
 */
class LoginActivity: BaseActivity(), LoginContract.View {
    private val mPresenter = LoginPresenter(this)

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        super.init()
        login.setOnClickListener { login() }
        newUser.setOnClickListener { startActivity<RegisterActivity>() }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }

    /**
     * 登陆
     */
    private fun login() {
        //隐藏软键盘
        CommonUtil.hideSoftKeyboard(this)
        //检查写sd卡权限
        val permission = hasWriteExternalStoragePermission()
        if(permission) {
            //有权限
            val usernameStr = userName.text.toString().trim()
            val passwordStr = password.text.toString().trim()
            mPresenter.login(usernameStr, passwordStr)
        } else applyWriteExternalStoragePermission()
    }

    /**
     * 申请写sd卡权限
     */
    private fun applyWriteExternalStoragePermission() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    /**
     * 是否有写sd卡权限
     */
    private fun hasWriteExternalStoragePermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //授权了
            login()
        } else toast(R.string.permission_denied)
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