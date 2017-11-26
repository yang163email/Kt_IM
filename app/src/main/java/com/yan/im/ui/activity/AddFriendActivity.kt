package com.yan.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.yan.im.R
import com.yan.im.adapter.AddFriendListAdapter
import com.yan.im.contract.AddFriendContract
import com.yan.im.presenter.AddFriendPresenter
import com.yan.im.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:05
 *  @description : 添加好友界面
 */
class AddFriendActivity: BaseActivity(), AddFriendContract.View {

    val mPresenter = AddFriendPresenter()

    override fun getLayoutId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }

        search.setOnClickListener { search() }
        userName.setOnEditorActionListener { v, actionId, event ->
            search()
            true
        }
    }

    /**
     * 搜索好友
     */
    private fun search() {
        CommonUtil.hideSoftKeyboard(this)
        showProgressDialog(getString(R.string.searching))
        val key = userName.text.toString().trim()
        mPresenter.search(key)
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