package com.example.counter

import android.content.SharedPreferences
import kotlin.math.max
import kotlin.math.min

private const val MINIMUM_VALUE = 0
private const val MAXIMUM_VALUE = Int.MAX_VALUE
private const val INITIAL_VALUE = MINIMUM_VALUE
private const val DECREMENT_MAGNITUDE = 1
private const val INCREMENT_MAGNITUDE = 1
private const val KEY_CURRENT_VALUE = "KEY_CURRENT_VALUE"

data class DisplayValues(
    val currentValueText: String,
    val isCurrentValueAtMinimumValue: Boolean,
    val isCurrentValueAtMaximumValue: Boolean,
    val isCurrentValueAtInitialValue: Boolean
)

fun readDisplayValues(sharedPreferences: SharedPreferences): DisplayValues {
    val currentValue = readCurrentValue(sharedPreferences)
    return DisplayValues(
        currentValueText = formatCurrentValue(currentValue),
        isCurrentValueAtMinimumValue = currentValue == MINIMUM_VALUE,
        isCurrentValueAtMaximumValue = currentValue == MAXIMUM_VALUE,
        isCurrentValueAtInitialValue = currentValue == INITIAL_VALUE
    )
}

fun decrement(sharedPreferences: SharedPreferences): Boolean =
    writeCurrentValue(
        sharedPreferences,
        readCurrentValue(sharedPreferences).run {
            max(this - DECREMENT_MAGNITUDE, MINIMUM_VALUE)
        }
    )

fun increment(sharedPreferences: SharedPreferences): Boolean =
    writeCurrentValue(
        sharedPreferences,
        readCurrentValue(sharedPreferences).run {
            min(this + INCREMENT_MAGNITUDE, MAXIMUM_VALUE)
        }
    )

fun reset(sharedPreferences: SharedPreferences): Boolean =
    writeCurrentValue(
        sharedPreferences,
        INITIAL_VALUE
    )

private fun readCurrentValue(sharedPreferences: SharedPreferences): Int =
    sharedPreferences.getInt(KEY_CURRENT_VALUE, INITIAL_VALUE)

private fun writeCurrentValue(sharedPreferences: SharedPreferences, currentValue: Int): Boolean =
    sharedPreferences
        .edit()
        .putInt(KEY_CURRENT_VALUE, currentValue)
        .commit()

private fun formatCurrentValue(currentValue: Int): String =
    currentValue.toString()

