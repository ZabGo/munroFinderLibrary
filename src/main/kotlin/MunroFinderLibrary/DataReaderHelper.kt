package MunroFinderLibrary

import java.io.File

fun getAllLinesFromFile(filePath: String): List<String> =
    File(filePath).readLines()

