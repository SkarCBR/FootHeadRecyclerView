package com.mrskar.samples.permissions

import android.app.Activity

interface PermissionHandler {

    fun checkPermission(activity: Activity, permission: String): Any
    fun requestLocationPermission(activity: Activity)
}
