package com.example.appmovie.core

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.Size
import androidx.core.app.ActivityCompat.requestPermissions
import com.example.appmovie.R
import com.example.appmovie.core.Constants.REQUEST_CODE_ALL_PERMISSION
import com.example.appmovie.ui.activities.main.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Utils @Inject constructor(@ApplicationContext private val context: Context) {

    fun showInfoDialog(context: Context, message: String) {
        val builder = MaterialAlertDialogBuilder(context)
        with(builder)
        {
            setMessage(message)
            setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    fun goToLogin() {

    }

    fun goToMain() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun goToAdmin() {

    }

    private fun hasPermissions(@Size(min = 1) vararg perms: String): Boolean =
        EasyPermissions.hasPermissions(context, *perms)

    val hasAllPermissions: Boolean = hasPermissions(*permissions.toTypedArray())

    fun requestAllPermissions(activity: Activity) {
        requestPermissions(
            activity,
            context.getString(R.string.global_message_permissions_all),
            REQUEST_CODE_ALL_PERMISSION,
            *permissions.toTypedArray()
        )
    }

    private val permissions: MutableList<String>
        get() {
            val permissions = mutableListOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) permissions.add(Manifest.permission.READ_PHONE_STATE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
                permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            }
            return permissions
        }

    private fun requestPermissions(
        host: Activity,
        rationale: String,
        requestCode: Int,
        @Size(min = 1) vararg perms: String
    ) {
        EasyPermissions.requestPermissions(
            PermissionRequest.Builder(host, requestCode, *perms)
                .setRationale(rationale)
                .build()
        )
    }

}