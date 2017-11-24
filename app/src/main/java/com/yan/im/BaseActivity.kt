package com.yan.im

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 14:53
 *  @description : 所有Activity的基类
 */
abstract class BaseActivity: AppCompatActivity() {

    val mProgressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    /** 初始化的一些操作 */
    open fun init() {}

    /** 布局文件 */
    abstract fun getLayoutId(): Int

    /**
     * 显示加载对话框
     */
    fun showProgressDialog(message: String) {
        mProgressDialog.setMessage(message)
        mProgressDialog.show()
    }

    /**
     * 隐藏加载对话框
     */
    fun hideProgressDialog() {
        if (mProgressDialog.isShowing) mProgressDialog.dismiss()
    }

}