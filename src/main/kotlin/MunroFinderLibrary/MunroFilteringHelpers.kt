package MunroFinderLibrary

fun List<SimplifiedMunro>.filteringByCategory(category: MunroCategory): List<SimplifiedMunro> {
    return when (category) {
        is MunroCategory.Munro -> this.filter { it.hillCategory == category.name }
        is MunroCategory.MunroTop -> this.filter { it.hillCategory == category.name }
        is MunroCategory.Either -> this
    }
}

fun List<SimplifiedMunro>.filteringByMinimumHeight(minimumHeight: Int?): List<SimplifiedMunro> =
    minimumHeight?.let { height -> this.filter { it.heightMeter >= height } } ?: this