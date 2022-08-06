package it.marcodallaba.duskrisetest

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.marcodallaba.duskrisetest.data.remote.AppleRepository
import it.marcodallaba.duskrisetest.data.remote.responses.Song
import it.marcodallaba.duskrisetest.util.Resource
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SongsListViewModel @Inject constructor(private val repository: AppleRepository) :
    ViewModel() {

    var songList = mutableStateOf<List<Song>>(listOf())

    init {
        loadSongs()
    }

    fun loadSongs() {
        viewModelScope.launch {
            val result = repository.getSongs()
            songList.value = result.data!!
        }
    }
}