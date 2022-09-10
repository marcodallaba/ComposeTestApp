package it.marcodallaba.compostetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import dagger.hilt.android.AndroidEntryPoint
import it.marcodallaba.compostetest.songList.SongListFragment
import it.marcodallaba.compostetest.ui.theme.ComposeTestTheme
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTestTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "song_list_fragment"
                ) {
                    composable("song_list_fragment") {
                        SongListFragment(navController = navController)
                    }
                    composable(
                        "web_view_fragment/{url}",
                        arguments = listOf(navArgument("url") { type = NavType.StringType })
                    ) { backStackEntry ->

                        backStackEntry.arguments?.getString("url")?.let { encodedURL ->
                            val decodedURL =
                                URLDecoder.decode(encodedURL, StandardCharsets.UTF_8.toString())
                            val state = rememberWebViewState(decodedURL)

                            WebView(
                                state
                            )
                        }
                    }
                }
            }
        }


    }


}