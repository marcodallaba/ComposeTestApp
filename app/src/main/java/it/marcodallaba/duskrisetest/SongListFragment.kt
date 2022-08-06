package it.marcodallaba.duskrisetest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import it.marcodallaba.duskrisetest.data.remote.responses.Song

@Composable
fun SongListFragment(
    navController: NavController, viewModel: SongsListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Play List",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            SongList(navController = navController)
        }
    }

}

@Composable
fun SongList(navController: NavController, viewModel: SongsListViewModel = hiltViewModel()) {
    val songList by remember { viewModel.songList }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = songList.size / 3
        items(itemCount) {
            SongRow(rowIndex = it, entries = songList, navController = navController)
        }
    }
}

@Composable
fun SongRow(
    rowIndex: Int,
    entries: List<Song>,
    navController: NavController
) {
    Column {
        Row {
            SongEntry(
                entry = entries[rowIndex * 3],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))

            SongEntry(
                entry = entries[rowIndex * 3 + 1],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))

            SongEntry(
                entry = entries[rowIndex * 3 + 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SongEntry(
    entry: Song,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SongsListViewModel = hiltViewModel()
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
        /*
.clickable {
    navController.navigate(
        "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
    )
}

     */
    ) {
        Column {
            AsyncImage(
                model = entry.artworkUrl100,
                contentDescription = entry.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(10.dp)),
            )
            Text(
                text = entry.artistName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = entry.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = entry.releaseDate,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }


}