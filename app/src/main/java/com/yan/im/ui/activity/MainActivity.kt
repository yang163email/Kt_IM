package com.yan.im.ui.activity

import com.yan.im.R
import com.yan.im.utils.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:25
 *  @description ：主界面
 */
class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(it))
            transaction.commit()
        }
    }
}
