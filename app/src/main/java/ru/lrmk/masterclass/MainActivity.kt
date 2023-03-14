package ru.lrmk.masterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.lrmk.masterclass.ui.theme.MasterClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterClassTheme(darkTheme = true) {
                Surface {
                    val movies by produceState(emptyList<Movie>()) {
                        value = api.movies()
                    }
                    LazyVerticalGrid(GridCells.Adaptive(120.dp)) {
                        items(movies) {
                            AsyncImage(model = API.small + it.poster_path,
                                contentDescription = "",
                                Modifier.size(200.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
