package com.math.noteapp.android

import SampleData
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(Modifier.fillMaxSize())
                }

            }

        }
    }

}

data class City(val countryName: String, val details: String, val image: Int)
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab! \n Top City Tours",
            textAlign = TextAlign.Center,)
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Explore")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    cities: List<City> = SampleData.cities
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = cities){ city ->
           Greeting(city = city)

        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyApplicationTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Greeting(city: City, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .shadow(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        shape =  RoundedCornerShape(10.dp)
    ) {
        CardContent(city)
    }

}

@Composable
private fun CardContent(city: City){

    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 180.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                    //.padding(bottom = extraPadding.coerceAtLeast(0.dp))
                ) {
                    Text(text = city.countryName,
                        //  you can modify a predefined style by using the copy function
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ), color = Color(0xFFE67B2F)
                    )
                }
                IconButton( onClick = { expanded = !expanded }) {
                    Icon(imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription =if (expanded) "Show less" else "Show more")

                }

            }
            Column ( modifier = Modifier
                .fillMaxWidth()
                .size(extraPadding.coerceAtLeast(0.dp))){
                Card (
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    shape =  RoundedCornerShape(10.dp)){
                    Image(painter = painterResource(id = city.image), contentDescription ="city image",
                        modifier = Modifier.fillMaxWidth() ,
                        contentScale = ContentScale.Crop)
                }
                Text(text =city.details,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.W400
                    )
                )
            }

        }

    }
}
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greetings()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    MyApplicationTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

