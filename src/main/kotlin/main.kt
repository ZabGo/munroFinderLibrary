
import MunroFinderLibrary.MunroCategory
import MunroFinderLibrary.Result
import MunroFinderLibrary.filteringByCategory
import MunroFinderLibrary.getAllLinesFromFile
import MunroFinderLibrary.getListOfMunrosFromFile

//import MunroFinderLibrary.getListOfMunrosFromFile

fun main() {
    val listOfMunros = getListOfMunrosFromFile("/home/xavier/projects/MunroFinderLibrary/src/main/kotlin/munrotab_v6.2.csv")

    when(listOfMunros){
        is Result.Success<*> -> println("list of munros: ${listOfMunros.munros}")
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> println(listOfMunros.message)
        is Result.Error.MinimumHeightIsNegative -> println(listOfMunros.message)
        is Result.Error.MaximumHeightIsNegative -> println(listOfMunros.message)
        is Result.Error.FileReadingException -> println(listOfMunros.message)
    }
//   val filteredResult = listOfMunros.filteringByCategory(MunroCategory.Either())
//    println(filteredResult)


}

//val listOfMunros = mutableListOf<Munro>()
//lines.forEach {line ->
////        println("line: $line")
//    if(line.isNotEmpty() || line.isNotEmpty()){
//        val lineIntoList = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*\$)".toRegex())
//        if(lineIntoList[0].isNotBlank() || lineIntoList[0].isNotEmpty()){
//            lineIntoList[0].toIntOrNull()?.let {
//
//                listOfMunros.add(Munro(
//                    it,
//                    lineIntoList[1].toInt(),
//                    lineIntoList[2],
//                    lineIntoList[3],
//                    lineIntoList[4],
//                    lineIntoList[5],
//                    lineIntoList[6],
//                    lineIntoList[7],
//                    lineIntoList[8],
//                    lineIntoList[9].toFloat(),
//                    lineIntoList[10].toFloat(),
//                    lineIntoList[11],
//                    lineIntoList[12],
//                    lineIntoList[13],
//                    lineIntoList[14],
//                    lineIntoList[15].toInt(),
//                    lineIntoList[16].toInt(),
//                    lineIntoList[17],
//                    lineIntoList[18],
//                    lineIntoList[19],
//                    lineIntoList[20],
//                    lineIntoList[21],
//                    lineIntoList[22],
//                    lineIntoList[23],
//                    lineIntoList[24],
//                    lineIntoList[25],
//                    lineIntoList[26],
//                    lineIntoList[27],
//                    lineIntoList[28],
//                ))
//            }
//        }
//    }
//
//
//
//
//
//}