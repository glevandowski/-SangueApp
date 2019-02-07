package company.inside.sangue.util

import android.app.Activity
import android.app.AlertDialog

class AppDialogs(activity: Activity) {

    var builder:AlertDialog.Builder

    init { builder = AlertDialog.Builder(activity) }

    fun showDialog(title:String, message:String, messageButton:String) = builder.setTitle(title).setMessage(message).
                setPositiveButton(messageButton) {dialog, which -> }.create().show()
}