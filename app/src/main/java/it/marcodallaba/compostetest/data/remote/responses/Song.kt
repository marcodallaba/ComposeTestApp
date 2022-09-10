package it.marcodallaba.compostetest.data.remote.responses

class Song(
    val artistName: String,
    val name: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val url: String
) {

    override fun toString(): String {
        return "Song(artistName='$artistName', name='$name', artworkUrl100='$artworkUrl100', releaseDate=$releaseDate)"
    }
}
