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
        is Result.Error -> handleError(this)
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
        is Result.Error -> handleError(this)
    }
}