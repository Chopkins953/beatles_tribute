package com.example.thebeatles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object
    {
        private var instance : MainActivity? = null
        public fun getInstance() : MainActivity
        {
            return instance!!
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instance = this

        val navController = Navigation.findNavController(this, R.id.fragment3)
        bottomNavigationView.setupWithNavController(navController)

    }


}
