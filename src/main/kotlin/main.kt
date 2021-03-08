import MunroFinderLibrary.getAllLinesFromFile

fun main(){

    val lines = getAllLinesFromFile("/home/xavier/projects/MunroFinderLibrary/src/main/kotlin/munrotab_v6.2.csv")
    lines.forEach {
        val token = it.split(",")
        println(token) }
}