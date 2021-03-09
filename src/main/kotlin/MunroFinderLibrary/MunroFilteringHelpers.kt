package MunroFinderLibrary

fun Result.filteringByCategory(category: MunroCategory): Result {

    return when (this) {
        is Result.Success<*> -> {
            this.munros as List<SimplifiedMunro>
            when (category) {
                is MunroCategory.Munro -> Result.Success(this.munros.filter { it.hillCategory == category.name })
                is MunroCategory.MunroTop -> Result.Success(this.munros.filter { it.hillCategory == category.name })
                is MunroCategory.Either -> Result.Success(this.munros)
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileException -> Result.Error.FileException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}

fun Result.filteringByMinimumHeight(minimumHeight: Int? = null): Result {
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
        is Result.Error.FileException -> Result.Error.FileException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}

fun Result.filteringByMaximumHeight(maximumHeight: Int? = null): Result {
    return when (this) {
        is Result.Success<*> -> {
            val munros = this.munros as List<SimplifiedMunro>
            when {
                maximumHeight == null -> Result.Success(munros)
                maximumHeight < 0 -> Result.Error.MaximumHeightIsNegative()
                else -> Result.Success(munros.filter { it.heightMeter <= maximumHeight })
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileException -> Result.Error.FileException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}

fun Result.filteringByHeights(minimumHeight: Int? = null, maximumHeight: Int? = null): Result {
    return when (this) {
        is Result.Success<*> -> {
            when {
                minimumHeight != null && maximumHeight != null && minimumHeight > maximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
                else -> this.filteringByMinimumHeight(minimumHeight).filteringByMaximumHeight(maximumHeight)
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileException -> Result.Error.FileException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}

fun Result.limitNumberOfItemDisplayed(numberOfItems: Int): Result {
    return when (this) {
        is Result.Success<*> -> {
            when {
                numberOfItems < 0 -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
                else -> Result.Success(this.munros.take(numberOfItems))
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileException -> Result.Error.FileException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }

}