package com.example.tasks.task4

fun shakerSort(list: List<Int?>?): List<Int?>? {
    if (list == null || list.size < 2) return list

    val resultList = list.toMutableList()
    var temp: Int?
    var leftSide = 0
    var rightSide = resultList.size - 1

    do {
        for (i in leftSide..<rightSide) {
            if (resultList[i] == null && resultList[i + 1] != null ||
                (resultList[i + 1] != null && resultList[i]!! > resultList[i + 1]!!)) {
                temp = resultList[i]
                resultList[i] = resultList[i + 1]
                resultList[i + 1] = temp
            }
        }
        rightSide--
        for (i in rightSide downTo leftSide + 1) {
            if (resultList[i] != null && resultList[i - 1] != null && resultList[i]!! < resultList[i - 1]!! ||
                resultList[i] != null && resultList[i - 1] == null) {
                temp = resultList[i]
                resultList[i] = resultList[i - 1]
                resultList[i - 1] = temp
            }
        }
        leftSide++

    } while (leftSide < rightSide)
    return resultList
}


fun main() {
    val list1 = listOf(3, 5, 9, 7, 1, 6, 2, 8)
    val list2 = listOf(7,4,null, 22, null, 3, 5, null, null)
    val list3 = listOf(null, null, null)
    val list4 = null
    val list5 = listOf(null, 3)
    println(shakerSort(list1))
    println(shakerSort(list2))
    println(shakerSort(list3))
    println(shakerSort(list4))
    println(shakerSort(list5))
}