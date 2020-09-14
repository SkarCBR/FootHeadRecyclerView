package com.mrskar.samples.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

const val REQUEST_LOCATION_CODE = 999
const val FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
const val COARSE_LOCATION_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION

class PermissionHandlerImpl : PermissionHandler {

    private lateinit var sharedPreferences: SharedPreferences

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        val permissionResult = ActivityCompat.checkSelfPermission(context, permission)
        return permissionResult == PackageManager.PERMISSION_GRANTED
    }

    override fun checkPermission(
        activity: Activity,
        permission: String
    ): PermissionState {
        return if (isPermissionGranted(activity, permission)) {
            PermissionState.GRANTED
        } else {
            shouldAskPermission(activity, permission)
        }
    }

    private fun shouldAskPermission(activity: Activity, permission: String): PermissionState {
        return if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            PermissionState.DENIED
        } else {
            sharedPreferences =
                activity.getSharedPreferences("mrskar_sharedpreferences", MODE_PRIVATE)
            if (sharedPreferences.getBoolean(permission, true)) {
                sharedPreferences.edit().putBoolean(permission, false).apply()
                PermissionState.NEEDED
            } else {
                PermissionState.DENIED_NEVER_ASK
            }
        }
    }

    override fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(FINE_LOCATION_PERMISSION, COARSE_LOCATION_PERMISSION),
            REQUEST_LOCATION_CODE
        )
    }
}

enum class PermissionState {
    GRANTED,
    DENIED,
    NEEDED,
    DENIED_NEVER_ASK
}
