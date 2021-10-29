package fr.estia.net.batr.h.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import fr.estia.net.batr.h.activities.databinding.ComputeActivityBinding


class ComputeActivity: AppCompatActivity(), TextWatcher{
    private lateinit var binding: ComputeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ComputeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnCalculer.isEnabled = false
            field1.addTextChangedListener(this@ComputeActivity)
            field2.addTextChangedListener(this@ComputeActivity)

            btnCalculer.setOnClickListener {
                resultat.text = (field1.text.toString().toDouble() + field2.text.toString().toDouble()).toString()
            }
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        with(binding) {
            btnCalculer.isEnabled =
                !(field1.text.isEmpty() || field2.text.isEmpty())
        }
    }




}