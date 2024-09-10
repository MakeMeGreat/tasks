package com.example.tasks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tasks.databinding.ActivityMainBinding
import com.example.tasks.tasks1.Tasks1
import com.example.tasks.tasks2.task21.network.NewsAPI
import com.example.tasks.tasks2.task23.Task3RecyclerAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//task 1_1 и task1_2:

        // Описание задач внутри методов

        Tasks1().task_1_1()
        Tasks1().task_1_2()

//task 2_1:

        //Задача: Сделайте сетевой запрос и отобразите результат на экране? (база)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://newsApi.org")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        val apiService = retrofit.create(NewsAPI::class.java)
        compositeDisposable.add(
            apiService.getTopNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    binding!!.task1Result.text = response.articles[0].title
                }
        )

//task 2_2:

        //Задача: Сделайте таймер. TextView которая раз в секунду меняется (timer)
        compositeDisposable.add(
            Observable.interval(1000L, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding!!.task2Result.text = it.toString()
                }
        )
//task 2_3

        // Задача: Сделайте ресайклер. По нажатию на элемент передавайте его позицию во фрагмент.
        // и во фрагменте этот номер отображайте в тосте. (Subject)

        val dataList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val recyclerSubject = PublishSubject.create<Int>()
        val adapter = Task3RecyclerAdapter { position ->
            recyclerSubject.onNext(position)
        }
        adapter.submitList(dataList)
        binding!!.task3RecyclerView.adapter = adapter
        compositeDisposable.add(
            recyclerSubject.subscribe { position ->
                Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
            }
        )

//task 2_4

        //Задача: Сделайте EditText. При наборе текста выводите в лог содержимое EditText всегда,
        // когда пользователь 3 секунды что-то не вводил (debounce)

        val editTextSubject = PublishSubject.create<String>()
        binding!!.task4EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                editTextSubject.onNext(s.toString())
            }
        })
        compositeDisposable.add(
            editTextSubject
                .debounce(3, TimeUnit.SECONDS)
                .subscribe { text ->
                    Log.d("TASK_2_4", text)
                }
        )

//task 2_5

        //Задача: Есть 2 сервера на которых лежат скидочные карты.
        // Нужно получить эти данные и вывести в единый список. (zip и тд)
        //  а) Если 1 из запросов падает,
        //  то все равно выводить (найти метод RX для такого, чтоб самому не прописывать логику)
        //  б) Если 1 из запросов падает, то не выводить ничего (найти метод RX)

        val firstServer = Observable.create<String> {
            it.onNext("c1")
            it.onNext("c2")
            it.onNext("c3")
            it.onNext("c4")
            it.onNext("c5")
        }

        //task 2_5_a:

        val secondServerA = Observable.create<String> { server ->
            server.onNext("d1")
            server.onNext("d2")
            server.onNext("d3")
            server.onNext("d4")
            server.onError(IllegalArgumentException("Error"))
        }
            .onErrorReturnItem("Error")

        compositeDisposable.add(
            Observable.zip(firstServer, secondServerA) { item1, item2 -> "$item1 - $item2" }
                .subscribe { mergedText ->
                    binding!!.task5aResult.append("$mergedText" + "\n")
                }
        )

        //task 2_5_b:

        val secondServerB = Observable.create<String> { server ->
            server.onNext("d1")
            server.onNext("d2")
            server.onNext("d3")
            server.onNext("d4")
            server.onError(IllegalArgumentException("Error"))
        }
            .onErrorComplete()

        compositeDisposable.add(
            Observable.zip(firstServer, secondServerB) { item1, item2 -> "$item1 - $item2" }
                .subscribe { mergedText ->
                    binding!!.task5bResult.append("$mergedText" + "\n")
                }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}

