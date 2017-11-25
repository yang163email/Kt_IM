package com.yan.im.ui.activity

import android.os.Handler
import com.yan.im.R
import com.yan.im.contract.SplashContract
import com.yan.im.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 15:10
 *  @description : 欢迎页
 */
class SplashActivity: BaseActivity(), SplashContract.View {

    companion object {
        val DELAY = 2000L
    }

    private val handler by lazy { Handler() }
    private val presenter = SplashPresenter(this)

    override fun getLayoutId() = R.layout.activity_splash

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }
    /**
     * 未登录，跳转登录页
     */
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY)
    }

    /**
     * 已登录，跳转主页
     */
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }
}
