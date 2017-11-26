package com.yan.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.im.R

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
}