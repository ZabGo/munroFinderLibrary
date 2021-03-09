package MunroFinderLibrary

fun List<SimplifiedMunro>.filteringByCategory(category: String): List<SimplifiedMunro> =
    when (category) {
        MunroCategory.EITHER.type -> this
        else -> this.filter { it.hillCategory == category }
    }