package org.clintrorick

import java.io.File

class WriteArtistPairsToOutputCsv {
    fun doIt(listOfCommonArtistPairs : List<ArtistPair>){
        val outFileWriter = File( getOutputFilePath( ) ).writer( )
        listOfCommonArtistPairs.forEach{ outFileWriter.appendLine( it.pair.joinToString("+") ) }

        outFileWriter.flush()
        outFileWriter.close()
    }

    private fun getOutputFilePath() : String {
        return "output.csv"
    }
}