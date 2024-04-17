package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Get data from Intent
        val name = intent.getStringExtra("name")
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        // Display received data
        val textViewName: TextView = findViewById(R.id.textViewName)
        val textViewMainCourse: TextView = findViewById(R.id.textViewMainCourse)
        val textViewMainCourseQuantity: TextView = findViewById(R.id.textViewMainCourseQuantity)
        val textViewDrink: TextView = findViewById(R.id.textViewDrink)
        val textViewDrinkQuantity: TextView = findViewById(R.id.textViewDrinkQuantity)
        val textViewAddOns: TextView = findViewById(R.id.textViewAddOns)
        val textViewTotalPrice: TextView = findViewById(R.id.textViewTotalPrice)

        textViewName.text = if (name.isNullOrEmpty()) "訂購人: " else "訂購人: $name"
        textViewTotalPrice.text = "總金額: $totalPrice 元"

        // Display selected main course and quantity
        val imageViewMainCourse: ImageView = findViewById(R.id.imageViewMainCourse)
        val mainCourseId = intent.getIntExtra("mainCourseId", 0)
        val mainCourse = when (mainCourseId) {
            R.id.radioButtonBurger -> {
                imageViewMainCourse.visibility = View.VISIBLE
                imageViewMainCourse.setImageResource(R.drawable.berger)
                "漢堡(200元)"
            }
            R.id.radioButtonPasta -> {
                imageViewMainCourse.visibility = View.VISIBLE
                imageViewMainCourse.setImageResource(R.drawable.pasta)
                "焗麵(150元)"
            }
            R.id.radioButtonRice -> {
                imageViewMainCourse.visibility = View.VISIBLE
                imageViewMainCourse.setImageResource(R.drawable.rice)
                "燉飯(180元)"
            }
            else -> {
                imageViewMainCourse.visibility = View.GONE
                "未選擇主餐"
            }
        }
        val mainCourseQuantity = intent.getIntExtra("mainCourseQuantity", 0)
        textViewMainCourse.text = "$mainCourse"
        textViewMainCourseQuantity.text = "數量: $mainCourseQuantity"

        // Display selected drink and quantity
        val image: ImageView = findViewById(R.id.image)
        val drinkId = intent.getIntExtra("drinkId", 0)
        val drink = when (drinkId) {
            R.id.radioButtonCola -> {
                image.visibility = View.VISIBLE
                image.setImageResource(R.drawable.cola)
                "可樂(20元)"
            }
            R.id.radioButtonJuice -> {
                image.visibility = View.VISIBLE
                image.setImageResource(R.drawable.juice)
                "果汁(40元)"
            }
            R.id.radioButtonBeer -> {
                image.visibility = View.VISIBLE
                image.setImageResource(R.drawable.beer)
                "啤酒(60元)"
            }
            else -> {
                image.visibility = View.GONE
                "未選擇飲料"
            }
        }
        val drinkQuantity = intent.getIntExtra("drinkQuantity", 0)
        textViewDrink.text = "$drink"
        textViewDrinkQuantity.text = "數量: $drinkQuantity"

        // Display selected add-ons
        val friesChecked = intent.getBooleanExtra("friesChecked", false)
        val cakeChecked = intent.getBooleanExtra("cakeChecked", false)
        val iceCreamChecked = intent.getBooleanExtra("iceCreamChecked", false)
        var addonsText = ""
        if (friesChecked) {
            addonsText += "薯條(30元), "
        }
        if (cakeChecked) {
            addonsText += "蛋糕(40元), "
        }
        if (iceCreamChecked) {
            addonsText += "冰淇淋(50元), "
        }
        addonsText = addonsText.trimEnd(',', ' ')
        textViewAddOns.text = addonsText

        // Button to return to MainActivity
        val buttonReturn: Button = findViewById(R.id.buttonReturn)
        buttonReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
