package com.yan.im.adapter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMClient
import com.yan.im.R
import com.yan.im.model.data.ContactListItem
import com.yan.im.ui.activity.ChatActivity
import com.yan.im.utils.EMCallBackAdapter
import com.yan.im.widget.ContactListItemView
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:14
 *  @description : 联系人列表的adapter
 */
class ContactListAdapter(val context: Context, private val contactList: MutableList<ContactListItem>): RecyclerView.Adapter<ContactListAdapter.ContactListItemViewHolder>() {

    override fun getItemCount(): Int = contactList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactListItemViewHolder {
        val itemView = ContactListItemView(context)
        return ContactListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactListItemViewHolder, position: Int) {
        val itemView = holder.itemView as ContactListItemView
        itemView.bindView(contactList[position])
        val username = contactList[position].name
        //条目点击事件、跳转聊天界面
        itemView.setOnClickListener { context.startActivity<ChatActivity>("username" to username) }
        //长按删除好友操作
        val message = String.format(context.getString(R.string.delete_friend_message), username)
        itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                    .setTitle(R.string.delete_friend_title)
                    .setMessage(message)
                    .setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.confirm) {dialog, which ->
                        deleteFriend(username)
                    }
                    .show()
            true
        }
    }

    /**
     * 删除好友
     */
    private fun deleteFriend(username: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(username, object : EMCallBackAdapter{
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.delete_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.delete_friend_failed) }
            }

        })
    }

    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}