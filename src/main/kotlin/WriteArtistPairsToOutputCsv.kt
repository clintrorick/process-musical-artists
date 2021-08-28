import java.io.File

class WriteArtistPairsToOutputCsv {
    fun doIt(listOfCommonArtistPairs : List<ArtistPair>){
        val outFileWriter = File( getOutputFilePathWithTimestamp( ) ).writer( )
        listOfCommonArtistPairs.forEach{ outFileWriter.appendLine( it.pair.joinToString("+") ) }

        outFileWriter.flush()
        outFileWriter.close()
    }

    private fun getOutputFilePathWithTimestamp() : String {
        return "output_"+System.currentTimeMillis().toString()+".csv"
    }
}