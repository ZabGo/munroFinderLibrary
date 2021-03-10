import MunroFinderLibrary.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestMunroFinder {
    private val testListOfAllMunrosCategoryFromResult = Result.Success(
        listOf(
            SimplifiedMunro("Ben Chonzie", 931.0, "MUN", "NN773308"),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(name = "Ben More", heightMeter = 1174.0, hillCategory = "MUN", gridRef = "NN432244"),
            SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
            SimplifiedMunro(
                name = "Stob Binnein - Stob Coire an Lochain",
                heightMeter = 1068.0,
                hillCategory = "TOP",
                gridRef = "NN438220"
            ),
            SimplifiedMunro(
                name = "Stob Binnein - Meall na Dige",
                heightMeter = 966.0,
                hillCategory = "TOP",
                gridRef = "NN450225"
            ),
            SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
            SimplifiedMunro(
                name = "Cruach Ardrain - Stob Garbh",
                heightMeter = 957.7,
                hillCategory = "TOP",
                gridRef = "NN411221"
            )
        )
    )

    private val testListOfAllMunrosCategory = listOf(
        SimplifiedMunro("Ben Chonzie", 931.0, "MUN", "NN773308"),
        SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
        SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
        SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
        SimplifiedMunro(name = "Ben More", heightMeter = 1174.0, hillCategory = "MUN", gridRef = "NN432244"),
        SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
        SimplifiedMunro(
            name = "Stob Binnein - Stob Coire an Lochain",
            heightMeter = 1068.0,
            hillCategory = "TOP",
            gridRef = "NN438220"
        ),
        SimplifiedMunro(
            name = "Stob Binnein - Meall na Dige",
            heightMeter = 966.0,
            hillCategory = "TOP",
            gridRef = "NN450225"
        ),

        SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
        SimplifiedMunro(
            name = "Cruach Ardrain - Stob Garbh",
            heightMeter = 957.7,
            hillCategory = "TOP",
            gridRef = "NN411221"
        )
    )
    private val testListOfMunrosOnly = listOf(
        SimplifiedMunro("Ben Chonzie", 931.0, "MUN", "NN773308"),
        SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
        SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
        SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
        SimplifiedMunro(name = "Ben More", heightMeter = 1174.0, hillCategory = "MUN", gridRef = "NN432244"),
        SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
        SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
    )
    private val testListOfTopOnly = listOf(
        SimplifiedMunro(
            name = "Stob Binnein - Stob Coire an Lochain",
            heightMeter = 1068.0,
            hillCategory = "TOP",
            gridRef = "NN438220"
        ),
        SimplifiedMunro(
            name = "Stob Binnein - Meall na Dige",
            heightMeter = 966.0,
            hillCategory = "TOP",
            gridRef = "NN450225"
        ),
        SimplifiedMunro(
            name = "Cruach Ardrain - Stob Garbh",
            heightMeter = 957.7,
            hillCategory = "TOP",
            gridRef = "NN411221"
        )
    )

    @Test
    fun shouldDisplayOnlyTopMunros() {
        val expected = testListOfTopOnly
        val actual = testListOfAllMunrosCategoryFromResult.filteringByCategory(MunroCategory.MunroTop())
        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }


    }

    @Test
    fun shouldDisplayOnlyMunros() {
        val expected = testListOfMunrosOnly
        val actual = testListOfAllMunrosCategoryFromResult.filteringByCategory(MunroCategory.Munro())

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayAllMunros() {
        val expected = testListOfAllMunrosCategory
        val actual = testListOfAllMunrosCategoryFromResult.filteringByCategory(MunroCategory.Either())

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayAllMunrosAboveACertainHeight() {
        val expected = listOf(
            SimplifiedMunro(name = "Ben More", heightMeter = 1174.0, hillCategory = "MUN", gridRef = "NN432244"),
            SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
            SimplifiedMunro(
                name = "Stob Binnein - Stob Coire an Lochain",
                heightMeter = 1068.0,
                hillCategory = "TOP",
                gridRef = "NN438220"
            ),
            SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
        )
        val actual = testListOfAllMunrosCategoryFromResult.filteringByHeights(1000, null)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayAllMunrosBelowACertainHeight() {
        val expected = listOf(
            SimplifiedMunro("Ben Chonzie", 931.0, "MUN", "NN773308"),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(
                name = "Stob Binnein - Meall na Dige",
                heightMeter = 966.0,
                hillCategory = "TOP",
                gridRef = "NN450225"
            ),
            SimplifiedMunro(
                name = "Cruach Ardrain - Stob Garbh",
                heightMeter = 957.7,
                hillCategory = "TOP",
                gridRef = "NN411221"
            )
        )
        val actual = testListOfAllMunrosCategoryFromResult.filteringByHeights(null, 1000)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayErrorIfMaximumHeightIsNegative() {
        val expected = "The maximum height cannot be negative."
        val actual = testListOfAllMunrosCategoryFromResult.filteringByHeights(null, -300)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayErrorIfMinimumHeightIsNegative() {
        val expected = "The minimum height cannot be negative."
        val actual = testListOfAllMunrosCategoryFromResult.filteringByHeights(-400, null)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayErrorIfMinimumHeightIsHigherThanMaximumHeight() {
        val expected = "The minimum height cannot be higher than the maximum height."
        val actual = testListOfAllMunrosCategoryFromResult.filteringByHeights(1200, 500)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }


    @Test
    fun shouldSortResultbyAscendingHeight() {
        val expected = listOf(
            SimplifiedMunro(name = "Ben Chonzie", heightMeter = 931.0, hillCategory = "MUN", gridRef = "NN773308"),
            SimplifiedMunro(
                name = "Cruach Ardrain - Stob Garbh",
                heightMeter = 957.7,
                hillCategory = "TOP",
                gridRef = "NN411221"
            ),
            SimplifiedMunro(
                name = "Stob Binnein - Meall na Dige",
                heightMeter = 966.0,
                hillCategory = "TOP",
                gridRef = "NN450225"
            ),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
            SimplifiedMunro(
                name = "Stob Binnein - Stob Coire an Lochain",
                heightMeter = 1068.0,
                hillCategory = "TOP",
                gridRef = "NN438220"
            ),
            SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
            SimplifiedMunro(
                name = "Ben More",
                heightMeter = 1174.0,
                hillCategory = "MUN",
                gridRef = "NN432244"
            )
        )

        val actual = testListOfAllMunrosCategoryFromResult.sortedByHeight(SortResult.Ascending)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldSortResultbyDescendingHeight() {
        val expected = listOf(
            SimplifiedMunro(name = "Ben Chonzie", heightMeter = 931.0, hillCategory = "MUN", gridRef = "NN773308"),
            SimplifiedMunro(
                name = "Cruach Ardrain - Stob Garbh",
                heightMeter = 957.7,
                hillCategory = "TOP",
                gridRef = "NN411221"
            ),
            SimplifiedMunro(
                name = "Stob Binnein - Meall na Dige",
                heightMeter = 966.0,
                hillCategory = "TOP",
                gridRef = "NN450225"
            ),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
            SimplifiedMunro(
                name = "Stob Binnein - Stob Coire an Lochain",
                heightMeter = 1068.0,
                hillCategory = "TOP",
                gridRef = "NN438220"
            ),
            SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
            SimplifiedMunro(
                name = "Ben More",
                heightMeter = 1174.0,
                hillCategory = "MUN",
                gridRef = "NN432244"
            )
        )

        val actual = testListOfAllMunrosCategoryFromResult.sortedByHeight(SortResult.Ascending)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldSortResultbyAscendingName() {
        val expected = listOf(
            SimplifiedMunro(name = "Ben Chonzie", heightMeter = 931.0, hillCategory = "MUN", gridRef = "NN773308"),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(
                name = "Ben More",
                heightMeter = 1174.0,
                hillCategory = "MUN",
                gridRef = "NN432244"
            ),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
            SimplifiedMunro(
                name = "Cruach Ardrain - Stob Garbh",
                heightMeter = 957.7,
                hillCategory = "TOP",
                gridRef = "NN411221"
            ),
            SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
            SimplifiedMunro(
                name = "Stob Binnein - Meall na Dige",
                heightMeter = 966.0,
                hillCategory = "TOP",
                gridRef = "NN450225"
            ),
            SimplifiedMunro(
                name = "Stob Binnein - Stob Coire an Lochain",
                heightMeter = 1068.0,
                hillCategory = "TOP",
                gridRef = "NN438220"
            ),
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
        )

        val actual = testListOfAllMunrosCategoryFromResult.sortedByName(SortResult.Ascending)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldSortResultbyDescendingName() {
        val expected = listOf(
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
            SimplifiedMunro(
                name = "Stob Binnein - Stob Coire an Lochain",
                heightMeter = 1068.0,
                hillCategory = "TOP",
                gridRef = "NN438220"
            ),
            SimplifiedMunro(
                name = "Stob Binnein - Meall na Dige",
                heightMeter = 966.0,
                hillCategory = "TOP",
                gridRef = "NN450225"
            ),
            SimplifiedMunro(name = "Stob Binnein", heightMeter = 1165.0, hillCategory = "MUN", gridRef = "NN434227"),
            SimplifiedMunro(
                name = "Cruach Ardrain - Stob Garbh",
                heightMeter = 957.7,
                hillCategory = "TOP",
                gridRef = "NN411221"
            ),
            SimplifiedMunro(name = "Cruach Ardrain", heightMeter = 1045.9, hillCategory = "MUN", gridRef = "NN409212"),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(
                name = "Ben More",
                heightMeter = 1174.0,
                hillCategory = "MUN",
                gridRef = "NN432244"
            ),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(name = "Ben Chonzie", heightMeter = 931.0, hillCategory = "MUN", gridRef = "NN773308"),







            )

        val actual = testListOfAllMunrosCategoryFromResult.sortedByName(SortResult.Desending)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shoulddisplayALimitedNumberOfItems() {
        val expected = listOf(
            SimplifiedMunro("Ben Chonzie", 931.0, "MUN", "NN773308"),
            SimplifiedMunro(name = "Ben Vorlich", heightMeter = 985.0, hillCategory = "MUN", gridRef = "NN629189"),
            SimplifiedMunro(name = "Stuc a' Chroin", heightMeter = 975.0, hillCategory = "MUN", gridRef = "NN617174"),
            SimplifiedMunro(name = "Ben Lomond", heightMeter = 974.0, hillCategory = "MUN", gridRef = "NN367028"),
            SimplifiedMunro(name = "Ben More", heightMeter = 1174.0, hillCategory = "MUN", gridRef = "NN432244"),
        )

        val actual = testListOfAllMunrosCategoryFromResult.limitNumberOfItemDisplayed(5)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
            is Result.Error.NumberOfItemToDisplayCannotBeNegative -> assertEquals(expected, actual.message)
        }
    }

    @Test
    fun shouldDisplayErrorIfNumberIsNegative() {
        val expected = "The number of item to display cannot be negative."

        val actual = testListOfAllMunrosCategoryFromResult.limitNumberOfItemDisplayed(-5)

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> assertEquals(expected, actual.message)
            is Result.Error.MinimumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.MaximumHeightIsNegative -> assertEquals(expected, actual.message)
            is Result.Error.FileException -> assertEquals(expected, actual.message)
            is Result.Error.NumberOfItemToDisplayCannotBeNegative -> assertEquals(expected, actual.message)
        }
    }

}