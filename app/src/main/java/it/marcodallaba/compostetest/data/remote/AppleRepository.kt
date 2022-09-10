package it.marcodallaba.compostetest.data.remote

import dagger.hilt.android.scopes.ActivityScoped
import it.marcodallaba.compostetest.data.remote.responses.Song
import it.marcodallaba.compostetest.util.Resource
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class AppleRepository @Inject constructor(private val AppleApi: AppleApi) {

    suspend fun getSongs(): Resource<List<Song>> {
        val response = try {
            AppleApi.getSongs().body()!!.feed.results
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}