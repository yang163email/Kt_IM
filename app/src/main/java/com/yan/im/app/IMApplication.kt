package com.yan.im.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.yan.im.BuildConfig

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 16:58
 *  @description : 自定义Application类
 */
class IMApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        EMOptions options = new EMOptions()
        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false)
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
//        options.setAutoTransferMessageAttachments(true)
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
//        options.setAutoDownloadThumbnail(true)
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        //bmob初始化
        Bmob.initialize(this, "0b0614db59730ab6559dcfb1f1255967")
    }
}