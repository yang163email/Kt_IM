package com.yan.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.yan.im.R
import com.yan.im.adapter.MessageListAdapter
import com.yan.im.contract.ChatContract
import com.yan.im.presenter.ChatPresenter
import com.yan.im.utils.CommonUtil
import com.yan.im.utils.EMMessageListenerAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 18:44
 *  @description : 聊天界面
 */
class ChatActivity: BaseActivity(), ChatContract.View {

    val mPresenter = ChatPresenter(this)
    lateinit var username: String
    val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            mPresenter.addMessage(username, p0)
            runOnUiThread { recyclerView.adapter.notifyDataSetChanged() }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEditText()

        send.setOnClickListener { send() }
        edit.setOnEditorActionListener { v, actionId, event ->
            send()
            true
        }
        initRecyclerView()
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, mPresenter.messages)
        }
    }

    private fun send() {
        CommonUtil.hideSoftKeyboard(this)
        val message = edit.text.toString()
        mPresenter.sendMessage(username, message)
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        username = intent.getStringExtra("username")
        val titleStr = String.format(getString(R.string.chat_title), username)
        headerTitle.text = titleStr
    }

    override fun onStartSendMessage() {
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter.notifyDataSetChanged()
        toast(R.string.send_message_success)
        //清空编辑框
        edit.text.clear()
    }

    override fun onSendMessageFailed() {
        recyclerView.adapter.notifyDataSetChanged()
        toast(R.string.send_message_failed)
    }
}