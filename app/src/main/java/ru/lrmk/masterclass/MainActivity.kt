package ru.lrmk.masterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.lrmk.masterclass.ui.theme.MasterClassTheme
import ru.lrmk.masterclass.ui.theme.Player
import ru.lrmk.masterclass.ui.theme.shadowText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterClassTheme(darkTheme = true) {
                Surface {
                    val movies by produceState(emptyList<Movie>()) {
                        value = api.movies()
                    }
                    var movie by remember {
                        mutableStateOf(0)
                    }
                    BackHandler(movie != 0) {
                        movie = 0
                    }
                    if (movie == 0) LazyVerticalGrid(GridCells.Adaptive(120.dp)) {
                        items(movies) {
                            AsyncImage(model = API.small + it.poster_path,
                                contentDescription = "",
                                Modifier
                                    .size(200.dp)
                                    .clickable { movie = it.id }
                            )
                        }
                    }
                    else Movie(movies.first { it.id == movie })
                }
            }
        }
    }
}

@Composable
fun Movie(movie: Movie) = Box {
    var show by remember {
        mutableStateOf(false)
    }
    BackHandler(show) {
        show = false
    }
    AsyncImage(model = API.big + movie.backdrop_path,
        contentDescription = "", Modifier.fillMaxSize().alpha(0.6f),
        contentScale = ContentScale.FillHeight
    )
    if (show)
        Player(videoURI = API.video + movie.video)
    else Column {
        Row(Modifier.fillMaxWidth().padding(10.dp), Arrangement.SpaceBetween) {
            Text(movie.vote_average.toString(), fontSize = 20.sp, style = shadowText)
            Text(movie.release_date.toString().takeLast(4), fontSize = 20.sp, style = shadowText)
        }
        Text(movie.name, Modifier.fillMaxWidth(),
            fontSize = 24.sp, style = shadowText, textAlign = TextAlign.Center
        )
        Text(movie.overview, Modifier.padding(10.dp),
            style = shadowText, textAlign = TextAlign.Justify
        )
        AsyncImage(model = API.big + movie.poster_path, contentDescription = "",
            Modifier.fillMaxWidth().clickable { show = true }.padding(10.dp)
        )
    }
}