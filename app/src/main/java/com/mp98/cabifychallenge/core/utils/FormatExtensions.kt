package com.mp98.cabifychallenge.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrencyFormat(locale: Locale = Locale.getDefault()): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2
    return formatter.format(this)
}