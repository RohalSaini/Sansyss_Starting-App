package com.example.sanssystechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.sanssystechnology.databinding.ActivityMainBinding
import com.example.sanssystechnology.fragment.SplashScreen
import com.example.sanssystechnology.util.Session
import com.example.sanssystechnology.fragment.dashbaord.DashboardScreen
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var session:Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        session = Session(this)

        if(session.loggedIn()) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack("home")
                .replace(R.id.container, DashboardScreen())
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,SplashScreen())
                .commit()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        /*
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Yes") { dialog, which -> //if user pressed "yes", then he is allowed to exit from application
            finishAffinity()
        }
        builder.setNegativeButton("No") { dialog, which -> //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        }.create().show() */
    }
}