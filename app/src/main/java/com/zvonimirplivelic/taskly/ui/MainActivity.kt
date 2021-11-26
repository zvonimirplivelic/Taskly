package com.zvonimirplivelic.taskly.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.zvonimirplivelic.taskly.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.nav_container_view))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_container_view)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}