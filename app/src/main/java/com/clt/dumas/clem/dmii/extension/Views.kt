package com.clt.dumas.clem.dmii.extension

import android.widget.EditText


fun EditText.toDouble():Double?{
    return text.toString().toDoubleOrNull() ?: run{
        error = "BORDEELL NOON"
        null
    }
}