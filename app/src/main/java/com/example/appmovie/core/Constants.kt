package com.example.appmovie.core

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object Constants {

    const val PREFER_NAME = "user_data.conf"

    const val REQUEST_CODE_ALL_PERMISSION = 13

    private var symbols: DecimalFormatSymbols = DecimalFormatSymbols(Locale.US)
    val dFormat = DecimalFormat("###,###,##0.00", symbols)

}