import MunroFinderLibrary.*

fun main() {
    val fileAbsolutePath = "/home/xavier/projects/MunroFinderLibrary/src/main/kotlin/munrotab_v6.2.csv"
    val listOfMunros = getListOfMunrosFromFile(fileAbsolutePath)

    val result = listOfMunros.filteringByHeights(null, 1320).filteringByCategory(MunroCategory.MunroTop())
    when (result) {
        is Result.Success<*> -> println("list of munros: ${result.munros}")
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> println(result.message)
        is Result.Error.MinimumHeightIsNegative -> println(result.message)
        is Result.Error.MaximumHeightIsNegative -> println(result.message)
        is Result.Error.FileReadingException -> println(result.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> println(result.message)
    }

    val sortedResult = result.limitNumberOfItemDisplayed(12).sortedByHeight(SortResult.Desending)
    when (sortedResult) {
        is Result.Success<*> -> println("munros sorted by height ascending: ${sortedResult.munros}")
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> println(sortedResult.message)
        is Result.Error.MinimumHeightIsNegative -> println(sortedResult.message)
        is Result.Error.MaximumHeightIsNegative -> println(sortedResult.message)
        is Result.Error.FileReadingException -> println(sortedResult.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> println(sortedResult.message)
    }
}
