package org.clintrorick
    /**
     * Assumptions in addition to those provided in the assignment instructions:
     * 1) The data in the input CSV has been pre-processed so that artists are uniquely identified by a case-and-whitespace-sensitive string representation of their name.
     *          E.g. "franz ferdinand", "Franz Ferdinand", and "FranzFerdinand" are treated by this algorithm as separate artists.
     * 2) Artists may show up multiple times in a single user's list.
     *          Regardless, each user's list will only count a given combination of artists once.
     */

    const val OCCURRENCE_THRESHOLD_FOR_INCLUSION_TO_OUTPUT_FILE = 50

    // these data classes are purely for readability and improved type safety over just using raw collections
    data class ArtistPair(val pair: Set<String>)
    data class RawUserArtistListFromInputCsv(val rawUserArtistListFromInputCsv: Set<String>)

    fun main(){
        val listOfMusicalArtistLists = IngestMusicalArtistListsFromCsv().doIt()

        val listOfCommonArtistPairs =
                findArtistPairsOccuringAtLeastThisManyTimes(
                    listOfMusicalArtistLists, OCCURRENCE_THRESHOLD_FOR_INCLUSION_TO_OUTPUT_FILE)

        WriteArtistPairsToOutputCsv().doIt( listOfCommonArtistPairs )
    }

    fun findArtistPairsOccuringAtLeastThisManyTimes(listOfMusicalArtistLists : List<RawUserArtistListFromInputCsv>,
                                                    occurrenceThreshold : Int) : List<ArtistPair> {
        return listOfMusicalArtistLists
            .map { userArtistList -> userArtistList.rawUserArtistListFromInputCsv.toSet().toList() }
            .flatMap { userArtistList ->
                userArtistList.flatMapIndexed {
                        index, artist -> pairUpArtistsFromUserList(index,artist,userArtistList)
                }
            }
            .groupingBy{ artistPair -> artistPair } // could just use "it", but this reads better IMO
                .eachCount()
            .filter { countOfArtistPairOccurrences -> countOfArtistPairOccurrences.value >= occurrenceThreshold }
            .map { artistPairsWithCountOverThreshold -> artistPairsWithCountOverThreshold.key }
    }

    private fun pairUpArtistsFromUserList(index:Int, artist:String, userArtistList: List<String>) : List<ArtistPair> {
        val listOfArtistPairsForUser = mutableListOf<ArtistPair>()

        userArtistList.subList(index + 1, userArtistList.size).forEach { userArtistFromSubList ->
            listOfArtistPairsForUser.add( ArtistPair( setOf(artist, userArtistFromSubList) ) )
        }

        return listOfArtistPairsForUser.toList() //keep mutability isolated
    }



