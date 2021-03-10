import MunroFinderLibrary.*
import java.nio.file.Paths

fun main() {
    val fileAbsolutePath = "${Paths.get("").toAbsolutePath()}/src/main/kotlin/munrotab_v6.2.csv"

    val listOfMunros = getListOfMunrosFromFile(fileAbsolutePath)

    val sortedResult = listOfMunros
        .filteringByHeights(900)
        .filteringByCategory(MunroCategory.Munro())
        .limitNumberOfItemDisplayed(15)
        .sortedByName(SortResult.Ascending)
        .saveResultInFile()
    
    when (sortedResult) {
        is Result.Success<*> -> println("munros sorted by height ascending: ${sortedResult.munros}")
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> println(sortedResult.message)
        is Result.Error.MinimumHeightIsNegative -> println(sortedResult.message)
        is Result.Error.MaximumHeightIsNegative -> println(sortedResult.message)
        is Result.Error.FileException -> println(sortedResult.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> println(sortedResult.message)
    }

}
