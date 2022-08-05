package it.marcodallaba.duskrisetest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.marcodallaba.duskrisetest.data.remote.iTunesRepository
import it.marcodallaba.duskrisetest.util.Resource
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SongsListViewModel @Inject constructor(private val repository: iTunesRepository) :
    ViewModel() {

    init {
        loadSongs()
    }

    fun loadSongs() {
        viewModelScope.launch {
            val result = repository.getSongs()
            if (result is Resource.Success) {
                Timber.d(result.data.toString())
            }
        }
    }
}