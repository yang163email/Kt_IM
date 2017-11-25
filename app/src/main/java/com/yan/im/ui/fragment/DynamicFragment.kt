package com.yan.im.ui.fragment

import com.hyphenate.chat.EMClient
import com.yan.im.R
import com.yan.im.ui.activity.LoginActivity
import com.yan.im.utils.EMCallBackAdapter
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:40
 *  @description : 动态fragment
 */
class DynamicFragment : BaseFragment() {
    override fun getResLayoutId(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)
        logout.text = String.format(getString(R.string.logout), EMClient.getInstance().currentUser)

        logout.setOnClickListener { logout() }
    }

    /**
     * 退出登录
     */
    private fun logout() {
        EMClient.getInstance().logout(true, object : EMCallBackAdapter{
            override fun onSuccess() {
                context.runOnUiThread {
                    toast(R.string.logout_success)
                    context.startActivity<LoginActivity>()
                    activity.finish()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread {
                    toast(R.string.logout_failed)
                }
            }
        })
    }
}