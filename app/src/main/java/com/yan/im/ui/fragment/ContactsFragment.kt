package com.yan.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hyphenate.chat.EMClient
import com.yan.im.R
import com.yan.im.adapter.ContactListAdapter
import com.yan.im.contract.ContactContract
import com.yan.im.model.data.ContactListItem
import com.yan.im.presenter.ContactPresenter
import com.yan.im.ui.activity.AddFriendActivity
import com.yan.im.utils.EMContactListenerAdapter
import com.yan.im.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:40
 *  @description : 联系人fragment
 */
class ContactsFragment : BaseFragment(), ContactContract.View {

    private val mPresenter = ContactPresenter(this)

    private val contactListener = object : EMContactListenerAdapter() {
        override fun onContactDeleted(p0: String?) {
            //重新获取联系人数据
            mPresenter.loadContacts()
        }

        override fun onContactAdded(p0: String?) {
            //删除联系人时，刷新列表
            mPresenter.loadContacts()
        }
    }

    override fun getResLayoutId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        initHeader()
        initSwipeRefreshLayout()
        initRecyclerView()
        EMClient.getInstance().contactManager().setContactListener(contactListener)
        initSlideBar()

        //加载列表数据
        mPresenter.loadContacts()
    }

    private fun initSlideBar() {
        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSlideFinish() {
                section.visibility = View.GONE
            }

            override fun onSectionChange(firstLetter: String) {
                section.text = firstLetter
                section.visibility = View.VISIBLE
                //RecyclerView滚动到指定位置
                recyclerView.smoothScrollToPosition(getPosition(firstLetter))
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, mPresenter.contactList)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { mPresenter.loadContacts() }
        }
    }

    private fun initHeader() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener { context.startActivity<AddFriendActivity>() }
    }

    /**
     * 由首字母获取RecyclerView对应条目的位置
     */
    private fun getPosition(firstLetter: String): Int =
        mPresenter.contactList.binarySearch {
            it.firstLetter.minus(firstLetter[0])
        }

    override fun loadContactsSuccess(contactList: MutableList<ContactListItem>) {
        swipeRefreshLayout.isRefreshing = false
        //刷新数据
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun loadContactsFailed() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        //页面销毁，移除监听器
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }
}