package com.example.tasks

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

//Напишите аналоги операторов throttleFirst и throttleLatest из RxJava для Flow.

/*
* Эмиттит только то значение, которое придет после задрежки duration
*/
suspend fun <T> Flow<T>.throttleFirst(duration: Long): Flow<T> = flow {
    var lastEmitTime = 0L
    var currentTime: Long
    collect { value ->
        currentTime = System.currentTimeMillis()
        if (currentTime - lastEmitTime >= duration) {
            lastEmitTime = currentTime
            emit(value)
        }
    }
}

/*
* Каждые duration миллисек эмиттит последнее полученное значение
*/
fun <T> Flow<T>.throttleLatest(duration: Long): Flow<T> = this
    .conflate()
    .transform {
        emit(it)
        delay(duration)
    }

fun main() {
    val flowToCheckThrottleFirst = flow<Int> {
        var counter = 1
        while (true) {
            emit(counter++)
            delay(100)
        }
    }
    runBlocking {
        flowToCheckThrottleFirst.throttleFirst(1000)
            .collect {
                println(it)
            }
    }

//Чтобы проверить throttleLatest, закоментируй строки 38-50
    val flowToCheckThrottleLatest = flow<Int> {
        while (true) {
            emit(0)
            delay(500)
            emit(1)
            delay(200)
            emit(2)
            delay(800)
            emit(3)
            delay(600)
            emit(4)
        }
    }
    runBlocking {
        flowToCheckThrottleLatest.throttleLatest(1000)
            .collect {
                println(it)
            }
    }
}
