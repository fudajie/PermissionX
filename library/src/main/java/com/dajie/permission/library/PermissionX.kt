package com.dajie.permission.library

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatCallback
import androidx.fragment.app.FragmentActivity

/**

 *  Created by DaJie on 2020/10/27

 */
object PermissionX {
    private const val TAG = "InsivibleFragment"
    fun request(activity: FragmentActivity,vararg permissions:String,callback: PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment!=null){
            existedFragment as InsivibleFragment
        }else{
            val insivibleFragment = InsivibleFragment()
            fragmentManager.beginTransaction().add(insivibleFragment,TAG).commitNow()
            insivibleFragment
        }

        fragment.requestNow(callback,*permissions)
    }
}