package com.example.myapplication.modules.core.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


object PermissionHelper {
    fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun hasPermissions(context: Context, permissions: Array<String>): ArrayList<String> {
        val remainingPermissions = arrayListOf<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                remainingPermissions.add(permission)
            }
        }

        return remainingPermissions
    }


    fun requestPermissions(activity: Activity, permissions: ArrayList<String>, requestCode: Int) {
        val permissionArray = getNonGrantedPermissions(activity, permissions)
        if (permissionArray != null) {
            ActivityCompat.requestPermissions(activity, permissionArray, requestCode)
        }
    }

    fun requestPermissions(fragment: Fragment, permissions: ArrayList<String>?, requestCode: Int) {
        if (permissions == null) {
            return
        }

        val permissionArray = getNonGrantedPermissions(fragment.requireContext(), permissions)
        if (permissionArray != null) {
            fragment.requestPermissions(permissionArray, requestCode)
        }
    }

    private fun getNonGrantedPermissions(
        context: Context,
        permissions: ArrayList<String>
    ): Array<String?>? {
        val permissionList: ArrayList<String> = ArrayList()

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionList.add(permission)
            }
        }
        if (permissionList.size > 0) {
            val permissionArray = arrayOfNulls<String>(permissionList.size)
            permissionList.toArray(permissionArray)
            return permissionArray
        }
        return null
    }

    fun shouldShowPermissionRationale(
        activity: Activity,
        permissions: Array<String>?
    ): Boolean {
        if (permissions == null) {
            return false
        }
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true
            }
        }
        return false
    }

    fun shouldShowPermissionRationale(
        fragment: Fragment,
        permissions: Array<String>?
    ): Boolean {
        if (permissions == null) {
            return false
        }
        for (permission in permissions) {
            if (fragment.shouldShowRequestPermissionRationale(permission)) {
                return true
            }
        }
        return false
    }

    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        listener: OnDialogCloseListener
    ) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                positiveButtonText,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                    listener.onDialogClose(dialogInterface, OnDialogCloseListener.TYPE_POSITIVE)
                })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                listener.onDialogClose(dialogInterface, OnDialogCloseListener.TYPE_NEGATIVE)
            })
        builder.show()
    }

    fun openSettingScreen(activity: Activity, requestCode: Int) {
        activity.startActivityForResult(
            Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + activity.packageName)
            ), requestCode
        )
    }

    fun openSettingScreen(fragment: Fragment, requestCode: Int) {
        fragment.startActivityForResult(
            Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + fragment.requireActivity().packageName)
            ), requestCode
        )
    }

    interface OnDialogCloseListener {

        fun onDialogClose(dialog: DialogInterface, buttonType: Int)

        companion object {
            val TYPE_POSITIVE = 1
            val TYPE_NEGATIVE = -1
        }
    }
}