package com.example.tasks.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tasks.R
import com.example.tasks.databinding.Fragment3Task1Binding

class Task1Fragment3 : BaseFragment<Fragment3Task1Binding>(R.layout.fragment3_task1),
    RouteProvider {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): Fragment3Task1Binding {
        return Fragment3Task1Binding.inflate(inflater, container, false)
    }

    override val router: Router
        get() = (activity as RouteProvider).router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            router.navigateTo(Task1Fragment1())
        }
        binding.previousButton.setOnClickListener {
            router.exit()
        }
    }
}