package com.example.counter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textViewCurrentValue: TextView
    private lateinit var buttonDecrement: Button
    private lateinit var buttonIncrement: Button
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCurrentValue = findViewById(R.id.textViewCurrentValue)
        buttonDecrement = findViewById(R.id.buttonDecrement)
        buttonIncrement = findViewById(R.id.buttonIncrement)
        buttonReset = findViewById(R.id.buttonReset)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        displayValues(readDisplayValues(sharedPreferences))

        buttonDecrement.setOnClickListener {
            if (decrement(sharedPreferences)) {
                displayValues(readDisplayValues(sharedPreferences))
            }
        }

        buttonIncrement.setOnClickListener {
            if (increment(sharedPreferences)) {
                displayValues(readDisplayValues(sharedPreferences))
            }
        }

        buttonReset.setOnClickListener {
            if (reset(sharedPreferences)) {
                displayValues(readDisplayValues(sharedPreferences))
            }
        }
    }

    fun displayValues(displayValues: DisplayValues) {
        displayValues.run {
            textViewCurrentValue.text = currentValueText
            buttonDecrement.isEnabled = !isCurrentValueAtMinimumValue
            buttonIncrement.isEnabled = !isCurrentValueAtMaximumValue
            buttonReset.isEnabled = !isCurrentValueAtInitialValue
        }
    }
}
