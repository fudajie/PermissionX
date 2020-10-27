package com.dajie.permission.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**

 *  Created by DaJie on 2020/10/26

 */
//typealias关键字可以用于给任意类型指定一个别名
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InsivibleFragment : Fragment() {
    private var callback: PermissionCallback? = null
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}