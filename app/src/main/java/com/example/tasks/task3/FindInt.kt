package com.example.tasks.task3

import android.util.Log

fun <T> List<T>.findInt() {
    this.forEach {
        if (it is Int) {
            Log.d("FindInt", it.toString())
        }
    }
}