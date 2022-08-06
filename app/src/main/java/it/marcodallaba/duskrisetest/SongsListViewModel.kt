package it.marcodallaba.duskrisetest

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.marcodallaba.duskrisetest.data.remote.AppleRepository
import it.marcodallaba.duskrisetest.data.remote.responses.Song
import it.marcodallaba.duskrisetest.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsListViewModel @Inject constructor(private val repository: AppleRepository) :
    ViewModel() {

    var songList = mutableStateOf<List<Song>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadSongs()
    }

    fun loadSongs() {
        isLoading.value = true
        loadError.value = ""
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSongs()
            if (result is Resource.Success) {
                loadError.value = ""
                isLoading.value = false
                songList.value = result.data!!
            }
            else if (result is Resource.Error) {
                loadError.value = result.message!!
                isLoading.value = false
            }

        }
    }
}