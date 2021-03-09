package MunroFinderLibrary

fun Result.sortedByHeight(sortOrder: SortResult): Result {
    return when(this){
        is Result.Success<*> -> {
            this.munros as List<SimplifiedMunro>
            when(sortOrder){
                SortResult.Ascending -> Result.Success(this.munros.sortedBy { it.heightMeter })
                SortResult.Desending -> Result.Success(this.munros.sortedByDescending { it.heightMeter })
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileReadingException -> Result.Error.FileReadingException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}

fun Result.sortedByName(sortOrder: SortResult): Result {
    return when(this){
        is Result.Success<*> -> {
            this.munros as List<SimplifiedMunro>
            when(sortOrder){
                SortResult.Ascending -> Result.Success(this.munros.sortedBy { it.name })
                SortResult.Desending -> Result.Success(this.munros.sortedByDescending { it.name })
            }
        }
        is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight()
        is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative()
        is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative()
        is Result.Error.FileReadingException -> Result.Error.FileReadingException(this.message)
        is Result.Error.NumberOfItemToDisplayCannotBeNegative -> Result.Error.NumberOfItemToDisplayCannotBeNegative()
    }
}