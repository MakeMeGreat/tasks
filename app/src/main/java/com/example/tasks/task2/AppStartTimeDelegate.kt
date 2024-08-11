package com.example.tasks.task2

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.reflect.KProperty

class AppStartTimeDelegate(private val lifecycleOwner: LifecycleOwner) {
    private var startTime: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (startTime == null) {
            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            startTime = dateFormat.format(Date())
        }
        logStartTime(startTime!!)
        return startTime!!
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        startTime = value
    }

    private fun logStartTime(startTime: String) {
        lifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            while (isActive) {
                Log.d("StartTime", startTime)
                delay(3000)
            }
        }
    }
}