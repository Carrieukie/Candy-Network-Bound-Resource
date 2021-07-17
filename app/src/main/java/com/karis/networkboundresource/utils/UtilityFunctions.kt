package com.karis.networkboundresource.utils

import android.content.Context
import android.content.pm.ActivityInfo
import android.widget.Toast

object UtilityFunctions {

     fun getOrientation(orientation: String): Int {
        if (orientation == "Landscape"){
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }else if (orientation == "Portrait"){
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}