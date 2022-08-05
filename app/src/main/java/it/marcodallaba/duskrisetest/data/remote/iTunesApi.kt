package it.marcodallaba.duskrisetest.data.remote

import it.marcodallaba.duskrisetest.data.remote.responses.SongsResponse
import retrofit2.Response
import retrofit2.http.GET

interface iTunesApi {

    @GET("us/music/most-played/30/songs.json")
    suspend fun getSongs(): Response<SongsResponse>
}