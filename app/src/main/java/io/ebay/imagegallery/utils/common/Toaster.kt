package io.ebay.imagegallery.utils.common

import android.content.Context


object Toaster {
    fun show(context: Context, text: CharSequence) {
        val toast = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT)

        toast.show()
    }
}