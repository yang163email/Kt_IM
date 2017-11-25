package com.yan.im.ui.fragment

import android.view.View
import com.yan.im.R
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:40
 *  @description : 联系人fragment
 */
class ContactsFragment : BaseFragment() {
    override fun getResLayoutId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
        }
    }
}