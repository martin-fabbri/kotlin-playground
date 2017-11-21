package basics

fun main(args: Array<String>) {
    val together = concat(listOf("a", "b", "c"), " - ")
    println(together)
}

fun concat(texts: List<String>, separator: String = ",") = texts.joinToString(separator)