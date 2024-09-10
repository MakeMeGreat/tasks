package com.example.tasks.tasks1

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.concurrent.TimeUnit

class Tasks1 {
    fun task_1_1() {
        /*
        Задача:
        Какой результат будет в логе:
        Observable.timer(10, TimeUnit.MILLISECONDS, Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .map {
                Log.d("HAHAHA", "mapThread = ${Thread.currentThread().name}")
            }
            .doOnSubscribe {
                Log.d("HAHAHA", "onSubscribeThread = ${Thread.currentThread().name}" )
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.single())
            .flatMap {
                Log.d("HAHAHA", "flatMapThread = ${Thread.currentThread().name}")
                Observable.just(it)
                    .subscribeOn(Schedulers.io())
            }
            .subscribe {
                Log.d("HAHAHA", "subscribeThread = ${Thread.currentThread().name}")
        }
         */

        //Решение:
        val disposable = Observable.timer(10, TimeUnit.MILLISECONDS, Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .map {
                Log.d("HAHAHA", "mapThread = ${Thread.currentThread().name}") //newThread
            }
            .doOnSubscribe {
                Log.d("HAHAHA", "onSubscribeThread = ${Thread.currentThread().name}" ) // computation
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.single())
            .flatMap {
                Log.d("HAHAHA", "flatMapThread = ${Thread.currentThread().name}") // single
                Observable.just(it)
                    .subscribeOn(Schedulers.io())
            }
            .subscribe {
                Log.d("HAHAHA", "subscribeThread = ${Thread.currentThread().name}") // cached - io
            }
    }

    fun task_1_2() {
        /*
        Задача:
        Какой результат будет в логе? Как переписать, чтоб все вывести (2 варианта есть)
        val subject = PublishSubject.create<String>()
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.subscribe { Log.d("TAG", it) }
         */

        /*
        Решение:
        В логах ничего не будет,т.к. это PublishSubject предыдущие элементы не сохраняются.
        Чтобы все вывести нужно на PublishSubject подписаться раньше или поменять его на ReplaySubject
         */
        val subject = PublishSubject.create<String>()
        subject.subscribe { Log.d("TAG", it) }
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")

        val replaySubject = ReplaySubject.create<String>()
        replaySubject.onNext("1")
        replaySubject.onNext("2")
        replaySubject.onNext("3")
        replaySubject.subscribe { Log.d("TAG2", it) }

    }
}