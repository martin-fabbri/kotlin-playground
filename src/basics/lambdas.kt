package basics

fun main(args: Array<String>) {
    val timesTwo: (Int) -> Int = {x: Int -> x*2}
    val add = {x: Int, y: Int -> x + y}
    val list = (1..100).toList()
    println(list.joinToString())
    println(list.filter { element -> element > 90 })
    println(list.filter { it > 90 })
    println(list.filter { it % 2 == 0 })
    println(list.filter { it.even() })
    println(list.filter(::isEven))
}

fun isEven(i: Int) = i % 2 == 0

fun Int.even() = this % 2 == 0