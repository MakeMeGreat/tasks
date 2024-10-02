package com.example.tasks.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VBinding : ViewBinding>(
    @LayoutRes val layoutRes: Int,
) : Fragment(layoutRes) {

    private var _binding: VBinding? = null
    protected val binding get() = requireNotNull(_binding)


    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = createBinding(inflater, container)
        return binding.root
    }

    protected abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VBinding

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}