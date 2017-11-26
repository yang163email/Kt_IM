package com.yan.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.yan.im.R
import com.yan.im.adapter.AddFriendListAdapter
import com.yan.im.contract.AddFriendContract
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:05
 *  @description : 添加好友界面
 */
class AddFriendActivity: BaseActivity(), AddFriendContract.View {

    override fun getLayoutId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }

    override fun onSearchSuccess() {
        dismissProgressDialog()
        toast(R.string.search_success)
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dismissProgressDialog()
        toast(R.string.search_failed)
    }
}