package com.yan.im.contract

import com.yan.im.presenter.BasePresenter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:29
 *  @description : 添加好友界面协议层
 */
interface AddFriendContract {
    interface Presenter: BasePresenter {
        fun search(key: String)
    }

    interface View {
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}