package MunroFinderLibrary

fun List<Munro>.filteringByCategory(category: String): List<Munro> =
    when (category) {
        MunroCategory.EITHER.type -> this
        else -> this.filter { it.category == category }
    }