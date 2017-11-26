package com.yan.im.ui.activity

import com.yan.im.R
import kotlinx.android.synthetic.main.header.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:05
 *  @description : 添加好友界面
 */
class AddFriendActivity: BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)
    }
}