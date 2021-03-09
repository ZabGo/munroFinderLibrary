package MunroFinderLibrary

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
    val heightMeter: Float,
    val heightFeet: Float,
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
    val heightMeter: Float,
    val hillCategory: String,
    val gridRef: String,
)

sealed class Result {
    data class Success(val munros: List<Munro>) : Result()
    sealed class Error : Result() {
        data class MinimumHeightHigherThenMaximumHeight(val message: String = "The minimum height cannot be higher than the maximum height.") :
            Error()

        data class MinimumHeightIsNegative(val message: String = "The minimum height cannot be negative.") : Error()
        data class MaximumHeightIsNegative(val message: String = "The maximum height cannot be negative.") : Error()
    }
}

enum class MunroCategory(val type: String) {
    MUNRO("MUN"),
    MUNRO_TOP("TOP"),
    EITHER("EITHER"),
}