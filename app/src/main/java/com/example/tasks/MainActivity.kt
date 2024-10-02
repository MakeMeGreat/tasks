package com.example.tasks

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tasks.task1.NavigationHolder
import com.example.tasks.task1.RouteProvider
import com.example.tasks.task1.Router
import com.example.tasks.task1.Task1Fragment1

class MainActivity : AppCompatActivity(), RouteProvider {
    private val navigationHolder: NavigationHolder = NavigationHolder(this, R.id.fragment_container_view)
    private val routerOfActivity: Router = Router(navigationHolder)

    override val router: Router
        get() = routerOfActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, Task1Fragment1())
                .commit()
        }
    }
}