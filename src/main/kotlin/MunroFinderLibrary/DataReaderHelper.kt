package MunroFinderLibrary

import java.io.File
import java.lang.Exception

fun getAllLinesFromFile(filePath: String): Result {
    return try {
        Result.Success(File(filePath).readLines())
    } catch (exception: Exception) {

        Result.Error.FileReadingException(message = exception.localizedMessage)
    }
}

fun convertLinesOfFileIntoListOfMunros(lines: Result): Result {
    val listOfMunros = mutableListOf<Munro>()
    return when (lines) {
        is Result.Success<*> -> {
            val munros: List<String> = lines.munros as List<String>
            munros.forEach { line ->
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
            Result.Success(listOfMunros)
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileReadingException -> Result.Error.FileReadingException(lines.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }


}

fun convertListOfMunrosIntoSimplifiedListOfMunros(listOfMunros: Result): Result {
    return when (listOfMunros) {
        is Result.Success<*> -> {
            val list = listOfMunros.munros as List<Munro>
            val simplifiedMunros = list.map {
                SimplifiedMunro(it.name, it.heightMeter, it.yearPost1997, it.gridRef)
            }
                .filter { it.hillCategory.isNotBlank() || it.hillCategory.isNotEmpty() }

            Result.Success(simplifiedMunros)
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileReadingException -> Result.Error.FileReadingException(listOfMunros.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}

fun getListOfMunrosFromFile(filePath: String): Result {
    val lines = getAllLinesFromFile(filePath)
    val listOfMunros = convertLinesOfFileIntoListOfMunros(lines)
    return convertListOfMunrosIntoSimplifiedListOfMunros(listOfMunros)

}



