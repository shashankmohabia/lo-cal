package com.example.lo_cal.utils.extensions

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.lo_cal.R

fun Fragment.getTextShareIntent(action_type: String, message: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = action_type
        putExtra(Intent.EXTRA_TEXT, message)
    }
    activity?.startActivity(
        Intent.createChooser(
            shareIntent,
            getString(R.string.share_intent_text)
        )
    )
}
