package com.example.tasks

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.databinding.ActivityMainBinding
import com.example.tasks.task2.AppStartTimeDelegate
import com.example.tasks.task3.findInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val startTime by AppStartTimeDelegate(this) //task2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = startTime  //task2

        //task3
        val list = listOf("String", false, 2.67, null, 3, 7, MainActivity())
        binding.findInt.setOnClickListener { list.findInt() }
    }
}