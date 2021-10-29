package fr.estia.net.batr.h.tp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private var positionInList: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.my_button)
        button.setOnClickListener(this)
        loadImage("https://img-19.ccm2.net/WNCe54PoGxObY8PCXUxMGQ0Gwss=/480x270/smart/d8c10e7" +
                "fd21a485c909a5b4c5d99e611/ccmcms-commentcamarche/20456790.jpg")
    }

    override fun onClick(view: View?) {
        val linkList = listOf("https://m.media-amazon.com/images/I/81BtPHW5tyL._SS500_.jpg",
        "https://media.senscritique.com/media/000000205815/source_big/The_Trooper_Live.jpg",
        "https://fringster.com/content/images/34996.jpg",
        "https://media.senscritique.com/media/000017592203/source_big/Creeping_Death.jpg")

//        linkList.random()

        loadImage(linkList[positionInList])
        positionInList++

        if (positionInList == linkList.size - 1){
            positionInList = 0
        }

        Toast.makeText(this, getString(R.string.you_clicked), Toast.LENGTH_LONG).show()
    }

    private fun loadImage(url: String) {
        Picasso.get()
            .load(url)
            .resize(600, 600)
            .centerCrop()
            .into(imageView)
    }

}