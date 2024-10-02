package com.example.tasks.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tasks.R
import com.example.tasks.databinding.Fragment2Task1Binding

class Task1Fragment2 : BaseFragment<Fragment2Task1Binding>(R.layout.fragment2_task1),
    RouteProvider {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): Fragment2Task1Binding {
        return Fragment2Task1Binding.inflate(inflater, container, false)
    }

    override val router: Router
        get() = (activity as RouteProvider).router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            router.navigateTo(Task1Fragment3())
        }
        binding.previousButton.setOnClickListener {
            router.exit()
        }
    }
}