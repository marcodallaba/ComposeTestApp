package it.marcodallaba.duskrisetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import it.marcodallaba.duskrisetest.ui.theme.DuskRiseTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DuskRiseTestTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "first_fragment"
                ) {
                    composable("first_fragment") {
                        SongListFragment(navController = navController)
                    }
                }
            }
        }


    }


}