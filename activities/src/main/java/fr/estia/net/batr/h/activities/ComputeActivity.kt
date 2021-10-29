package fr.estia.net.batr.h.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity




class ComputeActivity: AppCompatActivity(), TextWatcher{
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        editText1 = findViewById(R.id.field_1)
        editText2 = findViewById(R.id.field_2)
        textViewResult = findViewById(R.id.resultat)
        buttonCalculate = findViewById(R.id.btn_calculer)

        buttonCalculate.isEnabled = false
        editText1.addTextChangedListener(this)
        editText2.addTextChangedListener(this)

        buttonCalculate.setOnClickListener {
            textViewResult.text = (editText1.text.toString().toDouble() + editText2.text.toString().toDouble()).toString()

        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        buttonCalculate.isEnabled = !(editText1.text.isEmpty() || editText2.text.isEmpty())
    }




}