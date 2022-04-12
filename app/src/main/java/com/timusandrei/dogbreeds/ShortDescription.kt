package com.timusandrei.dogbreeds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.timusandrei.dogbreeds.models.Dog
import com.timusandrei.dogbreeds.singletons.DogStorage

class ShortDescription : AppCompatActivity() {
    private var barkCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_short_description)

        val dogImage : ImageView = findViewById(R.id.short_description_image)
        val dogName : TextView = findViewById(R.id.short_description_name)
        val arartmentLiving : RatingBar = findViewById(R.id.apartment_living)
        val familyDog : RatingBar = findViewById(R.id.family_dog)
        val trainable : RatingBar = findViewById(R.id.trainable)
        val firstTimeDog : RatingBar = findViewById(R.id.first_time_dog)
        val moreDetails : Button = findViewById<Button>(R.id.more_details_button)
        val wikiButton : Button = findViewById<Button>(R.id.short_description_wiki_button)

        val dogId : Int = intent.getIntExtra("dogId", 1)
        val dog : Dog? = DogStorage.getInstance().dogs.find { it.id == dogId }

        if (dog != null) {
            dogName.text = dog.name
            dogImage.setImageResource(dog.profileImage)
            arartmentLiving.rating = dog.apartmentLiving
            familyDog.rating = dog.familyDog
            trainable.rating = dog.trainable
            firstTimeDog.rating = dog.firstTimeDog
        }

        moreDetails.setOnClickListener(View.OnClickListener { v: View ->
            val switchActivityIntent = Intent(this, DogScreen::class.java)
            switchActivityIntent.putExtra("dogId", dog!!.id)
            startActivity(switchActivityIntent)
        })

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
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}