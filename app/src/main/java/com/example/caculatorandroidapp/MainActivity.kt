package com.example.caculatorandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.caculatorandroidapp.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private lateinit var binding: ActivityMainBinding
    private var formula = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button00.setOnClickListener(this)
        binding.button0.setOnClickListener(this)
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)
        binding.button9.setOnClickListener(this)
        binding.buttonPlus.setOnClickListener(this)
        binding.buttonMinus.setOnClickListener(this)
        binding.buttonEqual.setOnClickListener(this)
        binding.buttonDelete.setOnClickListener(this)
        binding.buttonDelete.setOnLongClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            binding.buttonEqual.id -> caculateDisplay()
            binding.buttonDelete.id -> deleteDisplay()
            else -> updateDisplay(v as Button)
        }
    }

    override fun onLongClick(v: View): Boolean{
        formula.clear()
        binding.display.text = formula.toString()
        return true
    }

    private fun clearDisplay() {
        formula.clear()
        binding.display.text = null
    }

    private fun deleteDisplay() {
        formula.deleteCharAt(formula.length - 1)
        binding.display.text = formula.toString()
    }

    private fun caculateDisplay() {
        var res: Int = 0
        var now: Int = 0
        var lastOperation = '+'
        formula.append('=')
        for(s in formula) {
            if (s.isDigit()) {
                now *= 10
                now += s.digitToInt()
            } else {
                when(lastOperation) {
                    '+' -> res += now
                    '-' -> res -= now
                }
                lastOperation = s
                now = 0
            }
        }
        formula.clear()
        formula.append(res)
        binding.display.text = res.toString()
    }

    private fun updateDisplay(b: Button) {
        formula.append(b.text)
        binding.display.text = formula.toString()
    }
}
