package com.example.androidstudyjam1.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


object ToastAndSnackbarExtFunctions {
    fun Context.makeLongToast(str: String) = Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    fun Context.makeShortToast(str: String) = Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    fun View.makeLongSnackbar(
        str: String,
        actionLabel: String? = null,
        action: ((View) -> Any)? = null
    ) =
        if (actionLabel != null && action != null) {
            Snackbar.make(this, str, Snackbar.LENGTH_LONG).setAction(actionLabel) { view ->
                action(view)
            }.show()
        } else {
            Snackbar.make(this, str, Snackbar.LENGTH_LONG).show()
        }

    fun View.makeShortSnackbar(
        str: String,
        actionLabel: String? = null,
        action: ((View) -> Any)? = null
    ) =
        if (actionLabel != null && action != null) {
            Snackbar.make(this, str, Snackbar.LENGTH_SHORT).setAction(actionLabel) { view ->
                action(view)
            }.show()
        } else {
            Snackbar.make(this, str, Snackbar.LENGTH_SHORT).show()
        }

    fun Fragment.makeLongToast(str: String) =
        Toast.makeText(requireContext(), str, Toast.LENGTH_LONG).show()

    fun Fragment.makeShortToast(str: String) =
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()

    fun Fragment.makeLongSnackbar(
        str: String,
        actionLabel: String? = null,
        action: ((View) -> Any)? = null
    ) =
        if (actionLabel != null && action != null) {
            Snackbar.make(this.requireView(), str, Snackbar.LENGTH_LONG)
                .setAction(actionLabel) { view ->
                    action(view)
                }.show()
        } else {
            Snackbar.make(this.requireView(), str, Snackbar.LENGTH_LONG).show()
        }

    fun Fragment.makeShortSnackbar(
        str: String,
        actionLabel: String? = null,
        action: ((View) -> Any)? = null
    ) =
        if (actionLabel != null && action != null) {
            Snackbar.make(this.requireView(), str, Snackbar.LENGTH_SHORT)
                .setAction(actionLabel) { view ->
                    action(view)
                }.show()
        } else {
            Snackbar.make(this.requireView(), str, Snackbar.LENGTH_SHORT).show()
        }
}