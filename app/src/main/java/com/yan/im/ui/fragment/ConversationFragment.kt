package com.yan.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.yan.im.R
import com.yan.im.adapter.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:40
 *  @description : 消息fragment
 */
class ConversationFragment: BaseFragment() {

    val conversations = mutableListOf<EMConversation>()

    override fun getResLayoutId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)
        initRecyclerView()
        loadConversations()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context, conversations)
        }
    }

    private fun loadConversations() {
        doAsync {
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter.notifyDataSetChanged() }
        }
    }

}