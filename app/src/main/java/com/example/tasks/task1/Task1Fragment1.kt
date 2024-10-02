package com.example.tasks.task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tasks.R
import com.example.tasks.databinding.Fragment1Task1Binding

class Task1Fragment1 : BaseFragment<Fragment1Task1Binding>(R.layout.fragment1_task1) {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): Fragment1Task1Binding {
        return Fragment1Task1Binding.inflate(inflater, container, false)
    }



}