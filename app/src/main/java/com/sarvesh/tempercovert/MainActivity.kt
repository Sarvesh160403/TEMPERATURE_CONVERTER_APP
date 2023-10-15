package com.sarvesh.tempercovert

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTemperature: EditText
    private lateinit var radioGroupUnits: RadioGroup
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult1: TextView
    private lateinit var textViewResult2: TextView
    private lateinit var textViewResultdata1:TextView
    private lateinit var textViewResultdata2:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTemperature = findViewById(R.id.editTextTemperature)
        radioGroupUnits = findViewById(R.id.radioGroupUnits)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult1 = findViewById(R.id.textViewResult1)
        textViewResult2 = findViewById(R.id.textViewResult2)
        textViewResultdata1=findViewById(R.id.textViewResultdata1)
        textViewResultdata2=findViewById(R.id.textViewResultdata2)

        buttonConvert.setOnClickListener(View.OnClickListener {
            convertTemperature()
        })
    }

    private fun convertTemperature() {
        val temperatureText = editTextTemperature.text.toString()
        if (temperatureText.isNotEmpty()) {
            val temperature = temperatureText.toDouble()
            val selectedRadioButtonId = radioGroupUnits.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val unit = radioButton.text.toString()

            val convertedTemperature1 = when (unit) {
                "Celsius" -> (temperature * 9/5) + 32
                "Fahrenheit" -> (temperature - 32) * 5/9
                "Kelvin" -> temperature - 273.15
                else -> temperature
            }

            val convertedTemperature2 = when (unit) {
                "Celsius" -> temperature + 273.15
                "Fahrenheit" -> (temperature - 32) * 5 / 9 + 273.15
                "Kelvin" -> (temperature - 273.15) * 9/5 + 32
                else -> temperature
            }

            val resultText1 = "${if (unit == "Fahrenheit" || unit=="Kelvin") "Celsius" else "Fahrenheit"}"
            val resultText2 = "${if(unit=="Celsius" || unit=="Fahrenheit")"Kelvin" else "Fahrenheit"}"
            textViewResult1.text = resultText1
            textViewResult2.text = resultText2
            textViewResultdata1.text= convertedTemperature1.toString()
            textViewResultdata2.text= convertedTemperature2.toString()

        } else {
            Toast.makeText(this,"Enter a temperature",Toast.LENGTH_SHORT).show()
        }
    }
}

