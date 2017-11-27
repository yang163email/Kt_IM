package com.yan.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.yan.im.R
import com.yan.im.adapter.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:40
 *  @description : 消息fragment
 */
class ConversationFragment: BaseFragment() {
    override fun getResLayoutId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context)
        }
    }
}