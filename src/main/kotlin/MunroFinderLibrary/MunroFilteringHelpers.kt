package MunroFinderLibrary

fun Result.filteringByCategory(category: MunroCategory = MunroCategory.Either()): Result {

    return when (this) {
        is Result.Success<*> -> {
            this.munros as List<SimplifiedMunro>
            when (category) {
                is MunroCategory.Munro -> Result.Success(this.munros.filter { it.hillCategory == category.name })
                is MunroCategory.MunroTop -> Result.Success(this.munros.filter { it.hillCategory == category.name })
                is MunroCategory.Either -> Result.Success(this.munros)
            }
        }
        is Result.Error -> handleError(this)
    }
}

fun Result.filteringByMinimumHeight(minimumHeight: Int? = null): Result {
    return when (this) {
        is Result.Success<*> -> {
            this.munros as List<SimplifiedMunro>
            when {
                minimumHeight == null -> Result.Success(this.munros)
                minimumHeight < 0 -> Result.Error.MinimumHeightIsNegative()
                else -> Result.Success(this.munros.filter { it.heightMeter >= minimumHeight })
            }
        }
        is Result.Error -> handleError(this)
    }
}

fun Result.filteringByMaximumHeight(maximumHeight: Int? = null): Result {
    return when (this) {
        is Result.Success<*> -> {
            this.munros as List<SimplifiedMunro>
            when {
                maximumHeight == null -> Result.Success(this.munros)
                maximumHeight < 0 -> Result.Error.MaximumHeightIsNegative()
                else -> Result.Success(this.munros.filter { it.heightMeter <= maximumHeight })
            }
        }
        is Result.Error -> handleError(this)
    }
}

fun Result.filteringByHeights(minimumHeight: Int? = null, maximumHeight: Int? = null): Result {
    return when (this) {
        is Result.Success<*> -> {
            when {
                (minimumHeight != null && minimumHeight >0) && (maximumHeight != null && maximumHeight > 0) && minimumHeight > maximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
                else -> this.filteringByMinimumHeight(minimumHeight).filteringByMaximumHeight(maximumHeight)
            }
        }
        is Result.Error -> handleError(this)
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
        is Result.Error -> handleError(this)
    }

}