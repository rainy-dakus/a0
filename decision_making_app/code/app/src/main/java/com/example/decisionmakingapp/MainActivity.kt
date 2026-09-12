package com.example.decisionmakingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionmakingapp.ui.theme.Blue
import com.example.decisionmakingapp.ui.theme.BlueBlack
import com.example.decisionmakingapp.ui.theme.DarkBlue
import kotlin.random.Random
import com.example.decisionmakingapp.ui.theme.DecisionMakingAppTheme
import com.example.decisionmakingapp.ui.theme.LightBlue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DecisionMakingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DecisionMakingApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DecisionMakingApp(modifier: Modifier = Modifier){
    var result by remember { mutableStateOf( "Should we go?") }
    var clickCount by remember { mutableIntStateOf( 0) }
    var yesCount by remember { mutableIntStateOf( 0) }
    var noCount by remember { mutableIntStateOf( 0) }
    var currentColor by remember { mutableStateOf(BlueBlack) }
    val imageResource = when (result) {
        "Yes" -> R.drawable.seal_happy
        "No" -> R.drawable.seal_sad
        else -> R.drawable.seal_neutral
    }

    Column(modifier = Modifier) {

        //Holds all main content
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier) {
                Text(text = result,
                    modifier = modifier,
                    fontWeight = FontWeight.Bold,fontSize = 36.sp,
                    color=currentColor
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Image(painter = painterResource(imageResource), contentDescription = "Seal drawing")

            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier) {

                Button(
                    onClick = {
                        result = generateResult(0.1)
                        clickCount++
                        if (result=="Yes"){
                            yesCount++
                        }else{
                            noCount++
                        }
                        currentColor = DarkBlue
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkBlue
                    )
                ) {
                    Text("No", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.width(18.dp))

                Button(
                    onClick = {
                        result = generateResult(0.25)
                        clickCount++
                        if (result=="Yes"){
                            yesCount++
                        }else{
                            noCount++
                        }
                        currentColor = Blue
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue
                    )
                ) {
                    Text("Maybe", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.width(18.dp))

                Button(
                    onClick = {
                        result = generateResult(0.5)
                        clickCount++
                        if (result=="Yes"){
                            yesCount++
                        }else{
                            noCount++
                        }
                        currentColor = LightBlue
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightBlue
                    )
                ) {
                    Text("Yes", fontSize = 24.sp)
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Box(modifier= Modifier){
                Text(text = "Clicks: $clickCount", modifier = modifier, fontSize = 18.sp)
            }
            Row(modifier= Modifier){
                Box(modifier= Modifier){Text(text = "'Yes' Outputs: $yesCount", modifier = modifier, fontSize = 18.sp)}
                Spacer(modifier = Modifier.width(25.dp))
                Box(modifier= Modifier){Text(text = "'No' Outputs: $noCount", modifier = modifier, fontSize = 18.sp)}
            }

        }

        //Holds screen bottom content
        Row(modifier= Modifier.padding(30.dp)){
            Box(modifier= Modifier){Text(text = "rainy 1836537", textAlign = TextAlign.Start,modifier = modifier, fontSize = 18.sp)}

        }

    }

}

fun generateResult(probability:Double):String{
    return if (Random.nextDouble()<probability){
        ("Yes")
    }else{
        ("No")
    }
}