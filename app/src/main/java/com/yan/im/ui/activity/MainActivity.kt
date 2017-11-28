package com.yan.im.ui.activity

import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.yan.im.R
import com.yan.im.utils.EMMessageListenerAdapter
import com.yan.im.utils.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:25
 *  @description ：主界面
 */
class MainActivity : BaseActivity() {

    val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            updateBottomBarUnReadCount()
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(it))
            transaction.commit()
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        EMClient.getInstance().addConnectionListener(object : EMConnectionListener{
            override fun onConnected() {

            }

            override fun onDisconnected(p0: Int) {
                if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    //发生多设备登录，跳转登录界面
                    startActivity<LoginActivity>()
                    finish()
                }
            }

        })
    }

    /**
     * 更新bottombar未读消息个数
     */
    private fun updateBottomBarUnReadCount() {
        val tab = bottomBar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onResume() {
        super.onResume()
        updateBottomBarUnReadCount()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}
