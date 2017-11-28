package com.yan.im.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.yan.im.BuildConfig
import com.yan.im.R
import com.yan.im.ui.activity.ChatActivity
import com.yan.im.utils.EMMessageListenerAdapter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 16:58
 *  @description : 自定义Application类
 */
class IMApplication: Application() {
    companion object {
        lateinit var instance: IMApplication
    }

    val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            if (isForeground()) {
                //如果是在前台，播放短的声音
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            }else {
                //如果是在后台，播放长的声音
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
                //弹出通知
                showNotification(p0)
            }
        }
    }

    /**
     * 弹出通知
     */
    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT) {
                contentText = (it.body as EMTextMessageBody).message
            }

            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("username", it.conversationId())
//            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = Notification.Builder(this)
                    .setContentTitle(getString(R.string.receive_new_message))
                    .setContentText(contentText)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.avatar1))
                    .setSmallIcon(R.mipmap.ic_contact)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()
            notificationManager.notify(1, notification)
        }
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)

    val duan by lazy {
        soundPool.load(instance, R.raw.duan, 0)
    }

    val yulu by lazy {
        soundPool.load(instance, R.raw.yulu, 0)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
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

        //在Application中监听消息状态
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    /**
     * 判断当前APP是否在前台
     */
    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses.forEach {
            if (it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true
            }
        }
        return false
    }
}