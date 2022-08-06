package it.marcodallaba.duskrisetest.data.remote

import dagger.hilt.android.scopes.ActivityScoped
import it.marcodallaba.duskrisetest.data.remote.responses.Song
import it.marcodallaba.duskrisetest.util.Resource
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class AppleRepository @Inject constructor(private val AppleApi: AppleApi) {

    suspend fun getSongs(): Resource<List<Song>> {
        val response = try {
            AppleApi.getSongs().body()!!.feed.results
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}