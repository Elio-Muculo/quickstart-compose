package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   ComposeQuadrantApp()
                }
            }
        }
    }
}


@Composable
fun ComposeQuadrantApp() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.first_title),
                description = stringResource(R.string.first_description),
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(R.string.second_title),
                description = stringResource(R.string.second_description),
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.third_title),
                description = stringResource(R.string.third_description),
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(R.string.fourth_title),
                description = stringResource(R.string.fourth_description),
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = Color.Red,
                textDirection = TextDirection.Rtl,
                shadow = Shadow(
                    color = Color.White,
                    blurRadius = 5f,
                )
            ),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}


@Composable
fun TaskManagerApp() {
    val imageUrl = painterResource(id = R.drawable.ic_task_completed)
    val message = stringResource(id = R.string.task_message)
    val feedback = stringResource(id = R.string.feedback)
    TaskManagerCard(image = imageUrl, message = message, feedback = feedback)
}

@Composable
fun TaskManagerCard(
    image: Painter,
    message: String,
    feedback: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = message,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = feedback,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ArticleApp() {
    val imageUrl = painterResource(id = R.drawable.bg_compose_background)
    val title = stringResource(id = R.string.article_title)
    val textOne = stringResource(id = R.string.first_paragraph)
    val textTwo = stringResource(id = R.string.second_paragraph)
    ArticleCard(
        imageUrl = imageUrl,
        title = title,
        textOne = textOne,
        textTwo = textTwo
    )
}

@Composable
fun ArticleCard(
    imageUrl: Painter,
    title: String,
    textOne: String,
    textTwo: String
) {
    Column() {
        Image(painter = imageUrl, contentDescription = null)
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(all = 16.dp)
        )
        Text(
            text = textOne,
            modifier = Modifier.padding(
                end = 16.dp,
                start = 16.dp
            ),
            textAlign = TextAlign.Justify
        )
        Text(
            text = textTwo,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(
                all = 16.dp
            )
        )
    }
}



@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {
    val image = painterResource(id = R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        BirthdayGreatingWithText(message = message, from = from)
    }
}


@Composable
fun BirthdayGreatingWithText(message: String, from: String) {
    Column {
        Text(
            text = message,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(
                    start = 16.dp,
                    top = 16.dp
                )
        )
        Text(
            text = " - from $from",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {

    }
}

