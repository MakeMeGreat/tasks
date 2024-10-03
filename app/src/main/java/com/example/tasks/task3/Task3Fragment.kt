package com.example.tasks.task3

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tasks.R
import com.example.tasks.databinding.FragmentTask3Binding
import com.example.tasks.task1.BaseFragment


class Task3Fragment : BaseFragment<FragmentTask3Binding>(R.layout.fragment_task3) {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTask3Binding {
        return FragmentTask3Binding.inflate(inflater, container, false)
    }
}