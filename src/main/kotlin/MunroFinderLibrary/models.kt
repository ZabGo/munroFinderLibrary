package MunroFinderLibrary

import javax.swing.SortOrder

data class Munro(
    val runningNumber: Int,
    val doBIHNumber: Int,
    val streetmap: String,
    val geograph: String,
    val hillBagging: String,
    val name: String,
    val smcSection: String,
    val rhbSection: String,
    val section: String,
    val heightMeter: Double,
    val heightFeet: Double,
    val map_150: String,
    val map_125: String,
    val gridRef: String,
    val gridRefXY: String,
    val xCoord: Int,
    val yCoord: Int,
    val year1891: String,
    val year1921: String,
    val year1933: String,
    val year1953: String,
    val year1969: String,
    val year1974: String,
    val year1981: String,
    val year1984: String,
    val year1990: String,
    val year1997: String,
    val yearPost1997: String,
    val comments: String = ""

)

data class SimplifiedMunro(
    val name:String,
    val heightMeter: Double,
    val hillCategory: String,
    val gridRef: String,
)

sealed class SortResult{
    object Ascending: SortResult()
    object Desending: SortResult()
}

sealed class Result {
    data class Success<T>(val munros: List<T>) : Result()
    sealed class Error : Result() {
        data class MinimumHeightHigherThenMaximumHeight(val message: String = "The minimum height cannot be higher than the maximum height.") :
            Error()
        data class MinimumHeightIsNegative(val message: String = "The minimum height cannot be negative.") : Error()
        data class MaximumHeightIsNegative(val message: String = "The maximum height cannot be negative.") : Error()
        data class FileException(val message: String) : Error()
        data class NumberOfItemToDisplayCannotBeNegative(val message: String = "The number of item to display cannot be negative.") : Error()
    }
}

sealed class MunroCategory() {
    data class Munro(val name: String = "MUN"): MunroCategory()
    data class MunroTop(val name: String = "TOP"): MunroCategory()
    data class Either(val name: String = "EITHER"): MunroCategory()
}