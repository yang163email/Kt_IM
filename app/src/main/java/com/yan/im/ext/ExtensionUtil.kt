package com.yan.im.ext

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/24 20:36
 *  @description : 扩展方法
 */

/**
 * 是否是有效的用户名。
 * 匹配规则：长度3-20位，首位为字母
 */
fun String.isValidUsername() = matches(Regex("^[a-zA-Z]\\w{2,19}$"))

/**
 * 是否是有效的密码。
 * 匹配规则：长度3-20位，全部为数字
 */
fun String.isValidPassword() = matches(Regex("^[0-9]{3,20}$"))

/**
 * 将 MutableMap 中的 key-value 取出，转换成可变参数 Pair 类型
 */
fun <K, V> MutableMap<K, V>.toVarargArray(): Array<Pair<K, V>> =
    map {
        Pair(it.key, it.value)
    }.toTypedArray()