package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var radioGroupMainCourse: RadioGroup
    private lateinit var radioGroupDrink: RadioGroup
    private lateinit var editTextMainCourseQuantity: EditText
    private lateinit var editTextDrinkQuantity: EditText
    private lateinit var checkBoxFries: CheckBox
    private lateinit var checkBoxCake: CheckBox
    private lateinit var checkBoxIceCream: CheckBox
    private lateinit var buttonCalculate: Button
    private lateinit var buttonReset: Button
    private lateinit var textViewResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        radioGroupMainCourse = findViewById(R.id.radioGroupMainCourse)
        radioGroupDrink = findViewById(R.id.radioGroupDrink)
        editTextMainCourseQuantity = findViewById(R.id.editTextMainCourseQuantity)
        editTextDrinkQuantity = findViewById(R.id.editTextDrinkQuantity)
        checkBoxFries = findViewById(R.id.checkBoxFries)
        checkBoxCake = findViewById(R.id.checkBoxCake)
        checkBoxIceCream = findViewById(R.id.checkBoxIceCream)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonReset = findViewById(R.id.buttonReset)
        textViewResult = findViewById(R.id.textViewResult)
        textViewResult.text = "您的點餐金額將會顯示於此"

        buttonCalculate.setOnClickListener {
            showConfirmationDialog()
        }

        buttonReset.setOnClickListener {
            resetValues()
        }
    }

    private fun calculateTotal() {
        // Get name
        val name = editTextName.text.toString()

        // Get selected main course and quantity
        val mainCoursePrice = when (radioGroupMainCourse.checkedRadioButtonId) {
            R.id.radioButtonBurger -> 200
            R.id.radioButtonPasta -> 150
            R.id.radioButtonRice -> 180
            else -> 0
        }
        val mainCourseQuantity = editTextMainCourseQuantity.text.toString().toIntOrNull() ?: 0

        // Get selected drink and quantity
        val drinkPrice = when (radioGroupDrink.checkedRadioButtonId) {
            R.id.radioButtonCola -> 20
            R.id.radioButtonJuice -> 40
            R.id.radioButtonBeer -> 60
            else -> 0
        }
        val drinkQuantity = editTextDrinkQuantity.text.toString().toIntOrNull() ?: 0

        val friesPrice = if (checkBoxFries.isChecked) 30 else 0
        val cakePrice = if (checkBoxCake.isChecked) 40 else 0
        val iceCreamPrice = if (checkBoxIceCream.isChecked) 50 else 0
        val addonsTotalPrice = friesPrice + cakePrice + iceCreamPrice


        // Calculate total price including add-ons
        val totalPrice = mainCoursePrice * mainCourseQuantity + drinkPrice * drinkQuantity + addonsTotalPrice

        // Pass data to MainActivity2
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("name", name)
            putExtra("totalPrice", totalPrice)
            putExtra("mainCourseId", radioGroupMainCourse.checkedRadioButtonId)
            putExtra("mainCourseQuantity", mainCourseQuantity)
            putExtra("drinkId", radioGroupDrink.checkedRadioButtonId)
            putExtra("drinkQuantity", drinkQuantity)
            putExtra("friesChecked", checkBoxFries.isChecked)
            putExtra("cakeChecked", checkBoxCake.isChecked)
            putExtra("iceCreamChecked", checkBoxIceCream.isChecked)
        }
        startActivity(intent)
    }

    private fun resetValues() {
        editTextName.text.clear()
        radioGroupMainCourse.clearCheck()
        radioGroupDrink.clearCheck()
        editTextMainCourseQuantity.text.clear()
        editTextDrinkQuantity.text.clear()
        checkBoxFries.isChecked = false
        checkBoxCake.isChecked = false
        checkBoxIceCream.isChecked = false
        textViewResult.text ="您的點餐金額將會顯示於此"
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("確認訂單")
        builder.setMessage("您確定要訂購這些餐點嗎？")
        builder.setPositiveButton("確認") { _, _ ->
            // User clicked on the confirmation button, execute total calculation
            calculateTotal()
        }
        builder.setNegativeButton("取消") { dialog, _ ->
            // User clicked on the cancel button, do nothing, close the dialog
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
