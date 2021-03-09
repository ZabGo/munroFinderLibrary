import MunroFinderLibrary.MunroCategory
import MunroFinderLibrary.Result
import MunroFinderLibrary.SimplifiedMunro
import MunroFinderLibrary.filteringByCategory
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
            SimplifiedMunro(
                name = "Cruach Ardrain SW Top",
                heightMeter = 1044.9,
                hillCategory = "",
                gridRef = "NN408211"
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
        SimplifiedMunro(
            name = "Cruach Ardrain SW Top",
            heightMeter = 1044.9,
            hillCategory = "",
            gridRef = "NN408211"
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
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight(
                actual.message
            )
            is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative(actual.message)
            is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative(actual.message)
            is Result.Error.FileReadingException -> Result.Error.FileReadingException(actual.message)
        }


    }

    @Test
    fun shouldDisplayOnlyMunros() {
        val expected = testListOfMunrosOnly
        val actual = testListOfAllMunrosCategoryFromResult.filteringByCategory(MunroCategory.Munro())

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight(
                actual.message
            )
            is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative(actual.message)
            is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative(actual.message)
            is Result.Error.FileReadingException -> Result.Error.FileReadingException(actual.message)
        }
    }

    @Test
    fun shouldDisplayAllMunros() {
        val expected = testListOfAllMunrosCategory
        val actual = testListOfAllMunrosCategoryFromResult.filteringByCategory(MunroCategory.Either())

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight(
                actual.message
            )
            is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative(actual.message)
            is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative(actual.message)
            is Result.Error.FileReadingException -> Result.Error.FileReadingException(actual.message)
        }
    }

    @Test
    fun shouldDisplayAllMunrosAboveACertainHeight() {
        val expected = testListOfAllMunrosCategory
        val actual = testListOfAllMunrosCategoryFromResult.filteringByCategory(MunroCategory.Either())

        when (actual) {
            is Result.Success<*> -> assertEquals(expected, actual.munros)
            is Result.Error.MinimumHeightHigherThenMaximumHeight -> Result.Error.MinimumHeightHigherThenMaximumHeight(
                actual.message
            )
            is Result.Error.MinimumHeightIsNegative -> Result.Error.MinimumHeightIsNegative(actual.message)
            is Result.Error.MaximumHeightIsNegative -> Result.Error.MaximumHeightIsNegative(actual.message)
            is Result.Error.FileReadingException -> Result.Error.FileReadingException(actual.message)
        }
    }

//    @Test
//    fun
}