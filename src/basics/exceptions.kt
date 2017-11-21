package basics

import java.io.IOException

fun main(args: Array<String>) {
    val input = try {
        val input = getExternalInput()
    } catch (e: IOException) {
        println("Caught an exception $e")
        ""
    } finally {
        println("Ending main execution")
    }
    println(input)
}

fun getExternalInput(): String {
    throw IOException("Could not read external input.")
}