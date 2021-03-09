package MunroFinderLibrary

import java.io.File
import java.lang.Exception

fun getAllLinesFromFile(filePath: String): List<String>? {
    return try {
        File(filePath).readLines()
    } catch (exception: Exception) {
        println("Exception: $exception")
        null
    }
}

fun convertLinesOfFileIntoListOfMunros(lines: List<String>?): MutableList<Munro> {
    val listOfMunros = mutableListOf<Munro>()
    lines?.forEach { line ->
        if (line.isNotEmpty() || line.isNotEmpty()) {
            val lineIntoList = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*\$)".toRegex())
            if (lineIntoList[0].isNotBlank() || lineIntoList[0].isNotEmpty()) {
                lineIntoList[0].toIntOrNull()?.let {

                    listOfMunros.add(
                        Munro(
                            it,
                            lineIntoList[1].toInt(),
                            lineIntoList[2],
                            lineIntoList[3],
                            lineIntoList[4],
                            lineIntoList[5],
                            lineIntoList[6],
                            lineIntoList[7],
                            lineIntoList[8],
                            lineIntoList[9].toDouble(),
                            lineIntoList[10].toDouble(),
                            lineIntoList[11],
                            lineIntoList[12],
                            lineIntoList[13],
                            lineIntoList[14],
                            lineIntoList[15].toInt(),
                            lineIntoList[16].toInt(),
                            lineIntoList[17],
                            lineIntoList[18],
                            lineIntoList[19],
                            lineIntoList[20],
                            lineIntoList[21],
                            lineIntoList[22],
                            lineIntoList[23],
                            lineIntoList[24],
                            lineIntoList[25],
                            lineIntoList[26],
                            lineIntoList[27],
                            lineIntoList[28],
                        )
                    )
                }
            }
        }


    }

    return listOfMunros
}

fun convertListOfMunrosIntoSimplifiedListOfMunros(munros: MutableList<Munro>): List<SimplifiedMunro> {
    return munros.map { SimplifiedMunro(it.name, it.heightMeter, it.yearPost1997, it.gridRef) }
        .filter { it.hillCategory.isNotBlank() || it.hillCategory.isNotEmpty() }
}

fun getListOfMunrosFromFile(filePath: String): List<SimplifiedMunro> {
    val lines = getAllLinesFromFile(filePath)
    val listOfMunros = convertLinesOfFileIntoListOfMunros(lines)

    return convertListOfMunrosIntoSimplifiedListOfMunros(listOfMunros)

}



