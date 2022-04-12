package com.timusandrei.dogbreeds

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timusandrei.dogbreeds.adapters.DogAdapter
import com.timusandrei.dogbreeds.models.Dog
import com.timusandrei.dogbreeds.singletons.DogStorage
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.dogs_recyclerview)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        val inputStream : InputStream = assets.open("data_source.xml")
        DogStorage.setXmlFile(inputStream)
        recyclerView.layoutManager = linearLayoutManager
        val adapter = DogAdapter(
            DogStorage.getInstance().dogs
        ) {
            dog -> showDog(dog)
        }
        recyclerView.adapter = adapter
    }

    private fun showDog(dog: Dog) {
        val switchActivityIntent = Intent(this, ShortDescription::class.java)
        switchActivityIntent.putExtra("dogId", dog.id)
        startActivity(switchActivityIntent)
    }
}