package com.yan.im.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 14:55
 *  @description : 所有Fragment的基类
 */
abstract class BaseFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(getResLayoutId(), null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) = init()

    /** 初始化的操作 */
    open fun init() {  }

    /** 布局文件 */
    abstract fun getResLayoutId(): Int
}