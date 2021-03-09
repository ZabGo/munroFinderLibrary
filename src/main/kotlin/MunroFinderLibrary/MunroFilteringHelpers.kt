package MunroFinderLibrary

fun List<SimplifiedMunro>.filteringByCategory(category: MunroCategory): List<SimplifiedMunro> {
    return when (category) {
        is MunroCategory.Munro -> this.filter { it.hillCategory == category.name }
        is MunroCategory.MunroTop -> this.filter { it.hillCategory == category.name }
        is MunroCategory.Either -> this
    }
}

//MunroCategory.EITHER.type -> this
//else -> this.filter { it.hillCategory == categoryType }