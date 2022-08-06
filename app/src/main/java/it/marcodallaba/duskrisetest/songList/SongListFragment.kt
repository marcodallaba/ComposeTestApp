package it.marcodallaba.duskrisetest.songList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import it.marcodallaba.duskrisetest.data.remote.responses.Song
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SongListFragment(
    navController: NavController
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
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = songList.size / 3
        items(itemCount) {
            SongRow(rowIndex = it, entries = songList, navController = navController)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadSongs()
            }
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
    modifier: Modifier = Modifier
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable {
                val encodedUrl = URLEncoder.encode(entry.url, StandardCharsets.UTF_8.toString())
                navController.navigate(
                    "web_view_fragment/$encodedUrl",
                )
            }


    ) {
        Column {
            AsyncImage(
                model = entry.artworkUrl100,
                contentDescription = entry.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(20.dp)),
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

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}