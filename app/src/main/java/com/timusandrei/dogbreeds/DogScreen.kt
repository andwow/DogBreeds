package com.timusandrei.dogbreeds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.timusandrei.dogbreeds.models.Dog
import com.timusandrei.dogbreeds.singletons.DogStorage

class DogScreen : AppCompatActivity() {

    private var barkCount = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_screen)

        val dogImage : ImageView = findViewById(R.id.dog_screen_image)
        val dogName : TextView = findViewById(R.id.dog_screen_name)
        val dogDescription : TextView = findViewById(R.id.dog_large_description)
        val arartmentLiving : RatingBar = findViewById(R.id.apartment_living)
        val familyDog : RatingBar = findViewById(R.id.family_dog)
        val trainable : RatingBar = findViewById(R.id.trainable)
        val firstTimeDog : RatingBar = findViewById(R.id.first_time_dog)
        val wikiButton : Button = findViewById<Button>(R.id.wiki_button)

        val dogId : Int = intent.getIntExtra("dogId", 1)
        val dog : Dog? = DogStorage.getInstance().dogs.find { it.id == dogId }

        if (dog != null) {
            dogName.text = dog.name
            dogImage.setImageResource(dog.profileImage)
            dogDescription.text = dog.longDescription
            arartmentLiving.rating = dog.apartmentLiving
            familyDog.rating = dog.familyDog
            trainable.rating = dog.trainable
            firstTimeDog.rating = dog.firstTimeDog
        }

        wikiButton.setOnClickListener(View.OnClickListener { v: View ->
            val switchActivityIntent = Intent(this, WikiActivity::class.java)
            switchActivityIntent.putExtra("url", dog!!.wikiUrl)
            startActivity(switchActivityIntent)
        })

        dogImage.setOnClickListener(View.OnClickListener {

            when(barkCount) {
                0 -> displayToast("Bark!")
                1 -> displayToast("Bark! Bark!")
                2 -> displayToast("Bark! Bark! Bark!")
                3 -> displayToast("I'm barking because I'm hungry, human!")
            }

            if(barkCount < 3) {
                ++barkCount
            } else {
                barkCount = 0
            }

        })
    }

    private fun displayToast(message : String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}