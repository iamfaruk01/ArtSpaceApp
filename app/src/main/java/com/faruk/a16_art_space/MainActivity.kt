package com.faruk.a16_art_space

import android.media.Image
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.faruk.a16_art_space.ui.theme._16_Art_SpaceTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _16_Art_SpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

val images = listOf(
    R.drawable.image1,
    R.drawable.image2,
    R.drawable.image3,
    R.drawable.image4
)
val titles = listOf(
    R.string.titleImage1,
    R.string.titleImage2,
    R.string.titleImage3,
    R.string.titleImage4,
)
val artists = listOf(
    R.string.artistImage1,
    R.string.artistImage2,
    R.string.artistImage3,
    R.string.artistImage4,
)
val years = listOf(
    R.string.yearImage1,
    R.string.yearImage2,
    R.string.yearImage3,
    R.string.yearImage4,
)

@Composable
fun ArtSpaceApp() {
    var currentIndex by remember { mutableStateOf(0) }

    ArtSpaceImageAndText(
        imageResourceId = images[currentIndex],
        onNextClick = {currentIndex = (currentIndex+1)% images.size},
        onPreviousClick = { currentIndex = (currentIndex + images.size - 1) % images.size },
        title = titles[currentIndex],
        artist = artists[currentIndex],
        year = years[currentIndex]
    )
}

@Composable
fun ArtSpaceImageAndText(
    imageResourceId: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    title: Int,
    artist: Int,
    year: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = modifier
                .padding(44.dp)
                .size(width = 300.dp, height = 400.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(8.dp),
                    spotColor = Color.Black
                )
//                .pointerInput(Unit) {
//                    detectHorizontalDragGestures { change, dragAmount ->
//                        change.consume()
//                        if (dragAmount > 40f) onNextClick()
//                        else if (dragAmount < -40f) onPreviousClick()
//                    }
//                },
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .padding(32.dp),
//                contentScale = ContentScale.FillHeight

            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = modifier
                .size(width = 310.dp, height = 100.dp)
                .background(color = Color(236, 235, 244))
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp),

        ) {
            Column(
//                verticalArrangemenlignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,

                )
                Row {
                    Text(
                        text = stringResource(artist),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(year),
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Row(
                modifier = modifier
//                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Button(
                    onClick = onPreviousClick,

                ) {
                    Text(
                        text = "Previous",
                        modifier = modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
                Button(onClick = onNextClick) {
                    Text(text = "Next",
                        modifier = modifier.padding(start = 32.dp, end = 32.dp)
                    )
                }
            }
    }
}