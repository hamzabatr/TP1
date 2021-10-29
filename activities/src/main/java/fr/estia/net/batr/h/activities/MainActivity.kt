package fr.estia.net.batr.h.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.estia.net.batr.h.activities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * car Android Studio a bien compris que clickButton sera initialisé
         * plus tard, donc si on ne l'initialise pas à un certain moment l'application va planter
         */

        with(binding) {
            btnClickMe.setOnClickListener {
                nbClick++
                val newText = "Vous avez cliqué $nbClick fois."
                textView.text = newText
                Toast.makeText(baseContext, "Tu m'as cliqué", Toast.LENGTH_LONG).show()

                btnClickMe.isEnabled = nbClick <= 4

            }//le toast apparait
        }

        binding.faireCalcul.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }
}