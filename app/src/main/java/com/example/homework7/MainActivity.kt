package com.example.homework7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.homework7.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            if (checkIfFieldIsEmpty()) {
                Snackbar.make(binding.root, "fill the field", Snackbar.LENGTH_SHORT).show()
            } else {
                val scrollView = binding.mainActivityLayout
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
                val newFieldLayout = TextInputLayout(this)
                newFieldLayout.layoutParams = layoutParams

                val newField = TextInputEditText(this)
                newField.layoutParams = layoutParams

                if (binding.cbToNumeric.isChecked) {
                    newField.inputType = InputType.TYPE_CLASS_NUMBER
                    newFieldLayout.hint = "${binding.etFieldTitle.text} - Numeric field"
                } else {
                    newField.inputType = InputType.TYPE_CLASS_TEXT
                    newFieldLayout.hint = "${binding.etFieldTitle.text} - Text field"
                }

                newFieldLayout.addView(newField)
                scrollView.addView(newFieldLayout)
                fieldClear()
            }
        }
    }

    private fun checkIfFieldIsEmpty(): Boolean = binding.etFieldTitle.text.toString().isEmpty()

    private fun fieldClear() {
        binding.etFieldTitle.text?.clear()
    }
}
