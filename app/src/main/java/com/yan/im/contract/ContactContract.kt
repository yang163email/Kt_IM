package com.yan.im.contract

import com.yan.im.presenter.BasePresenter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:22
 *  @description : 联系人界面协议
 */
interface ContactContract {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun loadContactsSuccess()
        fun loadContactsFailed()
    }
}