package com.yan.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yan.im.R
import com.yan.im.adapter.ContactListAdapter
import com.yan.im.contract.ContactContract
import com.yan.im.presenter.ContactPresenter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:40
 *  @description : 联系人fragment
 */
class ContactsFragment : BaseFragment(), ContactContract.View {

    private val mPresenter = ContactPresenter(this)

    override fun getResLayoutId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { mPresenter.loadContacts() }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter()
        }
        //加载列表数据
        mPresenter.loadContacts()
    }

    override fun loadContactsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        //刷新数据
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun loadContactsFailed() {
        swipeRefreshLayout.isRefreshing = false
    }
}