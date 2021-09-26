package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var colors = arrayListOf(
            "Red",
            "Black",
            "Pink",
            "White",
            "Blue",
            "Green",
            "Gray",
            "Brown",
            "Silver",
            "Purple"
        )
        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        myRV.adapter = RecyclerViewAdapter(colors)
        myRV.layoutManager = LinearLayoutManager(this)

        val addColorBT = findViewById<Button>(R.id.addBT)
        var colorName = findViewById<EditText>(R.id.editTextColor)
        colorName.setOnClickListener{
            colorName.setText("")
        }
        addColorBT.setOnClickListener {
            var myLayout = findViewById<ConstraintLayout>(R.id.clID)
            if (colorName.text.toString().isEmpty()) {
                Snackbar.make(myLayout, "Enter a color", Snackbar.LENGTH_LONG).show()
            } else if (checkColorExist(colorName.text.toString(), colors)) {
                Snackbar.make(
                    myLayout,
                    colorName.text.toString().capitalize() + " is already exist",
                    Snackbar.LENGTH_LONG
                ).show()

            } else {
                colors.add(colorName.text.toString())
                Snackbar.make(
                    myLayout,
                    colorName.text.toString() + " added to the list",
                    Snackbar.LENGTH_SHORT
                ).show()
                myRV.adapter!!.notifyDataSetChanged()
            }
            colorName.setText("")
        }

    }

    fun checkColorExist(color: String, colorsList: ArrayList<String>): Boolean {

        if (colorsList.contains(color.capitalize())) {
            return true
        }
        return false
    }
}