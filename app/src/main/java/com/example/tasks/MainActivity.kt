package com.example.tasks

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.tasks.task1.NavigationHolder
import com.example.tasks.task1.RouteProvider
import com.example.tasks.task1.Router
import com.example.tasks.task1.Task1Fragment1
import com.example.tasks.task2.ChargingWorker

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
        //Уведомление придет только если устройство находится на зарядке до старта приложения
        enqueueWorkManager()
    }

    private fun enqueueWorkManager() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        ) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            return
        }

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(ChargingWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

}