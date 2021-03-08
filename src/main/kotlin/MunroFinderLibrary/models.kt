package MunroFinderLibrary

data class Munro(val category: String, val height: Int, val name: String)

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
    MUNRO("MUNRO"),
    MUNRO_TOP("MUNRO_TOP"),
    EITHER("EITHER"),
}