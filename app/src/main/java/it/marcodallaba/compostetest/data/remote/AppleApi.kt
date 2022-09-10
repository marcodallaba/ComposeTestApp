package it.marcodallaba.compostetest.data.remote

import it.marcodallaba.compostetest.data.remote.responses.SongsResponse
import retrofit2.Response
import retrofit2.http.GET

interface AppleApi {

    @GET("us/music/most-played/30/songs.json")
    suspend fun getSongs(): Response<SongsResponse>
}