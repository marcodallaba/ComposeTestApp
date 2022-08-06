package it.marcodallaba.duskrisetest.data.remote.responses

class Song(
    val artistName: String,
    val name: String,
    val artworkUrl100: String,
    val releaseDate: String
) {

    override fun toString(): String {
        return "Song(artistName='$artistName', name='$name', artworkUrl100='$artworkUrl100', releaseDate=$releaseDate)"
    }
}
