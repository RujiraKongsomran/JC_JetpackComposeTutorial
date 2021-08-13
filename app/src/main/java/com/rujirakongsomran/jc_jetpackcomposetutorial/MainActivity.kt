package com.rujirakongsomran.jc_jetpackcomposetutorial

import SampleData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rujirakongsomran.jc_jetpackcomposetutorial.ui.theme.JC_JetpackComposeTutorialTheme
import com.rujirakongsomran.jc_jetpackcomposetutorial.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_JetpackComposeTutorialTheme {
//                MessageCard(Message("Android", "Jetpack Compose"))
//                Conversation(SampleData.conversationSample)
                Surface(color = MaterialTheme.colors.background) {
                    Row(
                        modifier = Modifier
                            .height(500.dp)
                            .width(500.dp)
                            .background(Color.LightGray),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        CustomItem(
                            weight = 3f,
                            color = MaterialTheme.colors.secondary
                        )
                        CustomItem(weight = 1f)
                    }
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun CustomText(mess: String) {
    Text(
        text = mess,
        style = Typography.h5
    )
}

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.girl),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // Variable
        var isExpanded by remember {
            mutableStateOf(false)
        }
        // SurfaceColor will bee update gradually from one color to the other
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        // We toggle the isExpanded variable when we click on this column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // SurfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)

                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
@Composable
fun PreviewMessageCard() {
    JC_JetpackComposeTutorialTheme {
        MessageCard(
            msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

//@Preview
@Composable
fun PreviewConversation() {
    JC_JetpackComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}

//@Preview(showBackground = true)
@Composable
fun PreviewCustomText() {
    JC_JetpackComposeTutorialTheme {
        Column {
            Greeting(name = "Android")
            CustomText(mess = "Android Spread")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC_JetpackComposeTutorialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                color = MaterialTheme.colors.primary
            ) {

            }
            Surface(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                color = MaterialTheme.colors.secondary
            ) {

            }
            Surface(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                color = MaterialTheme.colors.primary
            ) {

            }
            Surface(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                color = MaterialTheme.colors.secondary
            ) {

            }
        }
    }
}

@Composable
fun RowScope.CustomItem(
    weight: Float,
    color: Color = MaterialTheme.colors.primary
) {
    Surface(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .weight(weight),
        color = color
    ) {}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JC_JetpackComposeTutorialTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomItem(
                weight = 3f,
                color = MaterialTheme.colors.secondary
            )
            CustomItem(weight = 1f)
        }
    }
}

