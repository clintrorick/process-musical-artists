package org.clintrorick

import java.io.File

class IngestMusicalArtistListsFromCsv {

    private val pathToInputFile = "/app/artist_lists_small.csv"

    fun doIt() : List<RawUserArtistListFromInputCsv> {
        return File(pathToInputFile)
            .readLines(Charsets.UTF_8)
            .map{ userArtistRow -> userArtistRow.split(",") }
            .map{ it.toSet() }
            .map { RawUserArtistListFromInputCsv(it) }
    }

}