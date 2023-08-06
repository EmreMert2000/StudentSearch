package com.example.studentsearch

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.ButtonSearch.setOnClickListener{
          val textname=binding.TextName.toString()
          val textid=binding.TextID.toString()
          val textpoint=binding.TextPoint.toString()
            
        }

    }
}
