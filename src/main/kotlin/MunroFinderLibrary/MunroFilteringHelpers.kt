package MunroFinderLibrary

fun Result.filteringByCategory(category: MunroCategory): Result {

    return when (this) {
        is Result.Success<*> -> {
            val munros = this.munros as List<SimplifiedMunro>
            when (category) {
                is MunroCategory.Munro -> Result.Success(munros.filter { it.hillCategory == category.name })
                is MunroCategory.MunroTop -> Result.Success(munros.filter { it.hillCategory == category.name })
                is MunroCategory.Either -> Result.Success(munros)
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileReadingException -> Result.Error.FileReadingException(this.message)
    }
}

fun Result.filteringByMinimumHeight(minimumHeight: Int?): Result {
    return when (this) {
        is Result.Success<*> -> {
            val munros = this.munros as List<SimplifiedMunro>
            when {
                minimumHeight == null -> Result.Success(munros)
                minimumHeight < 0 -> Result.Error.MinimumHeightIsNegative()
                else -> Result.Success(munros.filter { it.heightMeter >= minimumHeight })
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileReadingException -> Result.Error.FileReadingException(this.message)
    }
}
//minimumHeight?.let { height -> this.filter { it.heightMeter >= height } } ?: this