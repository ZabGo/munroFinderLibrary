import MunroFinderLibrary.*
import java.nio.file.Paths

fun main() {
    val fileAbsolutePath = "${Paths.get("").toAbsolutePath()}/dataFolder/munrotab_v6.2.csv"

    val listOfMunros = getListOfMunrosFromFile(fileAbsolutePath)

    val sortedResult = listOfMunros
        .filteringByHeights(900, 1000)
        .filteringByCategory(MunroCategory.Munro())
        .sortedByName(SortResult.Ascending)
        .sortedByHeight(SortResult.Ascending)
        .limitNumberOfItemDisplayed(15)
        .saveResultInFile()

    when (sortedResult) {
        is Result.Success<*> -> println("munros: ${sortedResult.munros}")
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> println(sortedResult.message)
        is Result.Error.MinimumHeightIsNegative -> println(sortedResult.message)
        is Result.Error.MaximumHeightIsNegative -> println(sortedResult.message)
        is Result.Error.FileException -> println(sortedResult.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> println(sortedResult.message)
    }

}
