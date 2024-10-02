package com.example.tasks.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tasks.R
import com.example.tasks.databinding.Fragment3Task1Binding

class Task1Fragment3 : BaseFragment<Fragment3Task1Binding>(R.layout.fragment3_task1) {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): Fragment3Task1Binding {
        return Fragment3Task1Binding.inflate(inflater, container, false)
    }

}