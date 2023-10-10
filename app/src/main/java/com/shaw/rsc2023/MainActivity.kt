package com.shaw.rsc2023

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.shaw.rsc2023.databinding.ActivityMainBinding
import com.shaw.rsc2023.fragments.RoutesMainScreenFragment
import com.shaw.rsc2023.model_db.DbHelper
import com.shaw.rsc2023.model_db.DriverRouteDatabase

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createDb()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, RoutesMainScreenFragment()).commit()
    }

//    override fun onPause() {
//        if(isFinishing){
//            DbHelper.getInstance().closeRoomDb()
//        }
//        super.onPause()
//    }

    private fun createDb() {
        DbHelper.initialize(applicationContext)
    }

}