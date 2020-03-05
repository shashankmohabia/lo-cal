package com.example.lo_cal.Utils.Extensions

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.toast(message: String) {
    Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(message: String) {
    Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
}
