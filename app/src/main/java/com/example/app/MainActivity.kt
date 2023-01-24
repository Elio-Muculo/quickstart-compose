package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.app.afirmation.data.Datasource
import com.example.app.afirmation.model.Afirmation
import com.example.app.courses.Course
import com.example.app.dog.Dog
import com.example.app.ui.theme.AppTheme
import kotlin.text.Typography
import com.example.app.dog.dogs
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                AppTheme {
                    WoofApp()
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofApp() {
   Scaffold(
       topBar = {
           WoofAppBar()
       },
       content = { padding ->
           LazyColumn(modifier = Modifier
               .padding(padding)
               .background(MaterialTheme.colorScheme.background)) {
               items(dogs) {
                   DogItem(dog = it)
               }
           }
       }
   )
}

@Composable
fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val Green25 = Color(0xFFFF0E464D)
    val color by animateColorAsState(
        targetValue = if (expanded) Green25 else MaterialTheme.colorScheme.surface,
    )


    ElevatedCard(modifier = modifier.padding(8.dp)) {
        Column (
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogItemButton(expanded = expanded, onClick = {
                    expanded = !expanded
                })
            }
            if (expanded) DogHobby(dogHobby = dog.hobbies)
        }
    }
}


@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = dogIcon),
        contentDescription = null,
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(id = dogName),
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(id = R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun WoofAppBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.ic_woof_logo), contentDescription = null, modifier = Modifier
            .size(64.dp)
            .padding(8.dp))
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displayLarge)
    }
}

@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(id = R.string.expand_button_content_description)
        )
    }
}


@Preview
@Composable
fun DogIconPreview() {
    AppTheme {
        WoofAppBar()
    }
}

@Preview
@Composable
fun DarkDogIconPreview() {
    AppTheme (darkTheme = true) {
        WoofApp()
    }
}


@Preview
@Composable
fun BirthdayPreview() {
    AppTheme() {
        BirthdayGreetingWithImage("Happy Birthday Elio", "Elliot")
    }
}

@Composable
fun TopicApp() {
    val context = LocalContext.current
    AppTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(com.example.app.courses.DataSource.topics) { item ->
                TopicCard(topic = item)
            }
        }
    }
}


@Composable
fun TopicCard(topic: Course, modifier: Modifier = Modifier) {
    ElevatedCard (
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.outlinedCardElevation(),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(68.dp, 68.dp)
                    .aspectRatio(1f),
            )
            Column {
                Text(
                    text = stringResource(id = topic.text),
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null, modifier = Modifier
                        .padding(start = 16.dp)
                        .size(12.dp))
                    Text(
                        text = topic.value.toString(),
                        modifier = Modifier
                            .padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

// extract the modifier to top to not rebuild each time ui state change
val boxModifiers = Modifier
    .size(300.dp)

@Composable
fun ModifierCard() {
    Column (
        modifier = Modifier
            .requiredSize(200.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box (modifier = Modifier
            .clip(shape = CircleShape)
            .background(Color.Blue)
            .fillMaxSize()) {

        }
//       Row(modifier = Modifier.weight(4f).background(Color.Blue)) {
//           Text(text = "Hello")
//       }
//       Row(modifier = Modifier.weight(2f).background(Color.Black)) {
//           Text(text = "World")
//       }
//        Row(
//            modifier = Modifier
//                .align(Alignment.Center),
//            horizontalArrangement = Arrangement.spacedBy(
//                space = 20.dp,
//                alignment = Alignment.CenterHorizontally
//            )
//        ) {
//            Text(text = "Hello")
//            Text(text = "World")
//        }
    }
}



@Composable
fun BusinessCard() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.28f)
                .padding(horizontal = 5.dp)
        )
        Text(
            text = stringResource(id = R.string.name),
            style = TextStyle(
                fontSize = 36.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, top = 14.dp)
        )
        Text(
            text = stringResource(id = R.string.role),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF3dde84),
                fontWeight = FontWeight.Bold
            )
        )
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
fun AffirmationApp() {
    val context = LocalContext.current
    AppTheme {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
    }
}

@Composable
private fun AffirmationList(affirmationList: List<Afirmation>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(affirmationList){ affirmation ->
            AffirmationCard(affirmation)
        }
    }
}



@Composable
fun AffirmationCard(affirmation: Afirmation, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = Modifier.padding(8.dp)) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
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
