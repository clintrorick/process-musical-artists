import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ProcessMusicalArtistsTests {
    /**
     Focusing on testing the pure function with the most business logic, leaving out the I/O so this can run
     deterministically on a CI/CD server without messing with I/O perms.

     In a real situation, I would also pull in a mock filesystem component to properly test the I/O functions.
     **/
    @Test
    fun test_processArtists_OneArtistPairOverThreshold(){
        val rawUserArtistLists : List<RawUserArtistListFromInputCsv>
            = listOf(
                RawUserArtistListFromInputCsv(setOf("ABBA","U2")),
                RawUserArtistListFromInputCsv(setOf("Iron Maiden","Mastodon", "Alestorm")),
                RawUserArtistListFromInputCsv(setOf("Ghost","Finntroll","Alestorm","Iron Maiden"))
            )
        val artistPairsOverThreshold =
            findArtistPairsOccuringAtLeastThisManyTimes(rawUserArtistLists,2)

        assertTrue( artistPairsOverThreshold.contains( ArtistPair( setOf("Alestorm","Iron Maiden") ) ) )
        assertFalse( artistPairsOverThreshold.contains( ArtistPair( setOf("ABBA","U2") ) ) )
    }

    @Test
    fun verify_artistNames_areCaseAndWhitespaceSensitive(){
        val rawUserArtistLists : List<RawUserArtistListFromInputCsv>
                = listOf(
            RawUserArtistListFromInputCsv(setOf("Green Day","Dead Kennedys")),
            RawUserArtistListFromInputCsv(setOf("green day","dead kennedys")),
        )
        val artistPairsOverThresholdOf2 =
            findArtistPairsOccuringAtLeastThisManyTimes(rawUserArtistLists,2)

        assertFalse(
            artistPairsOverThresholdOf2.contains( ArtistPair( setOf("Green Day","Dead Kennedys") ) ) )
        assertFalse(
            artistPairsOverThresholdOf2.contains( ArtistPair( setOf("green day","dead kennedys") ) ) )

    }

    @Test
    fun verify_characters_from_different_lexicons_are_handled_properly(){
        val rawUserArtistLists : List<RawUserArtistListFromInputCsv>
                = listOf(
            RawUserArtistListFromInputCsv(setOf("ギルガメッシュ","Vypsaná fiXa")),
            RawUserArtistListFromInputCsv(setOf("Vypsaná fiXa","ギルガメッシュ")),
        )
        val artistPairsOverThresholdOf2 =
            findArtistPairsOccuringAtLeastThisManyTimes(rawUserArtistLists,2)

        assertTrue(
            artistPairsOverThresholdOf2.contains( ArtistPair( setOf("Vypsaná fiXa","ギルガメッシュ") ) ) )

    }




}