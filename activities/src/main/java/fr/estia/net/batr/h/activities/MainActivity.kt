package fr.estia.net.batr.h.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var clickButton: Button
    private lateinit var clickButtonCalcul: Button
    private lateinit var textView: TextView

    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * car Android Studio a bien compris que clickButton sera initialisé
         * plus tard, donc si on ne l'initialise pas à un certain moment l'application va planter
         */
        clickButton = findViewById(R.id.btn_click_me)
        textView = findViewById(R.id.text_view)
        clickButton.setOnClickListener {
            nbClick++
            val newText = "Vous avez cliqué $nbClick fois."
            textView.text = newText
            Toast.makeText(baseContext, "Tu m'as cliqué", Toast.LENGTH_LONG).show()
            if(nbClick == 5){
                clickButton.isEnabled = false
            }
        }//le toast apparait

        clickButtonCalcul = findViewById(R.id.faire_calcul)
        clickButtonCalcul.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }
}