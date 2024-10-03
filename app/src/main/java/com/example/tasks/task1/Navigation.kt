package com.example.tasks.task1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

open class Router(private val navigationHolder: NavigationHolder) {

    fun navigateTo(fragment: Fragment, backStackString: String? = null) {
        navigationHolder.fragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(navigationHolder.containerId, fragment, fragment::class.java.simpleName)
            .addToBackStack(backStackString)
            .commit()
    }

    fun backTo(fragment: Fragment, doIfFragmentNotExisting: () -> Unit = {}) {
        val isFragmentExistInBackStack =
            navigationHolder.fragmentManager.findFragmentByTag(fragment::class.java.name) != null
        if (isFragmentExistInBackStack) {
            navigationHolder.fragmentManager.popBackStack(fragment::class.java.name, 0)
        } else {
            doIfFragmentNotExisting()
        }
    }

    fun replaceScreen(fragment: Fragment, backStackString: String? = null) {
        navigationHolder.fragmentManager.popBackStack()
        navigationHolder.fragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(navigationHolder.containerId, fragment, fragment::class.java.name)
            .addToBackStack(backStackString)
            .commit()
    }

    fun exit() {
        navigationHolder.fragmentManager.popBackStack()
    }
}

class NavigationHolder(
    val activity: FragmentActivity,
    val containerId: Int,
    val fragmentManager: FragmentManager = activity.supportFragmentManager,
)

interface RouteProvider {
    val router: Router
}