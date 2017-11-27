package com.yan.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.yan.im.R
import com.yan.im.model.data.AddFriendItem
import com.yan.im.utils.EMCallBackAdapter
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:20
 *  @description : 添加好友列表item
 */
class AddFriendListItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_add_friend_item, this)
    }

    fun bindView(addFriendItem: AddFriendItem) {
        userName.text = addFriendItem.username
        timestamp.text = addFriendItem.timestamp
        if (addFriendItem.isAdded) {
            add.text = context.getString(R.string.already_added)
            add.isEnabled = false
        } else {
            add.text = context.getString(R.string.add)
            add.isEnabled = true
        }
        add.setOnClickListener { addFriend(addFriendItem.username) }
    }

    private fun addFriend(username: String) {
        EMClient.getInstance().contactManager().aysncAddContact(username, null, object : EMCallBackAdapter{
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.send_add_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.send_add_friend_failed) }
            }
        })
    }
}