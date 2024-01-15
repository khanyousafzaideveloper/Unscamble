package com.example.unscramble

import android.graphics.Outline
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun GameScreen(){
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Unscramble")

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var a : Int = 0
            Text(text = " $a / 10" )
            Text(text = "scrambleun")
            Text(text = "Unscamble the words using all letters")

            OutlinedTextField(value = "", onValueChange = { } )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Submit")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Skip")
        }
        var b : Int = 0
        Text(text = "Score: $b")
    }
}