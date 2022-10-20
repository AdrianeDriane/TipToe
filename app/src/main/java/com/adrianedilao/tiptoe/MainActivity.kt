package com.adrianedilao.tiptoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrianedilao.tiptoe.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()

        if (cost == null) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.rgTipOptions.checkedRadioButtonId) {
            R.id.rb_option_twenty_percent -> 0.20
            R.id.rb_option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (binding.switchRoundup.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvTipResult.text = getString(R.string.tipAmount, formattedTip)
    }

}