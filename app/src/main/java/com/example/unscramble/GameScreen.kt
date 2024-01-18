package com.example.unscramble

import android.app.Activity
import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun GameScreen(
    gameViewModel: GameViewModel = viewModel()
){
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Unscramble", fontFamily = FontFamily.SansSerif, fontSize = 40.sp)

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            val currentScrambledWord = gameUiState.currentScrambledWord
            val userGuess = gameViewModel.userGuess
            Row(modifier=Modifier.padding(16.dp)) {
                Text(text = "Score: ${gameUiState.score}" , fontWeight = FontWeight(800) , fontStyle= FontStyle(344))
                Spacer(modifier = Modifier.weight(2f))
                Box(
                    Modifier
                        .background(color = Color(143, 165, 133))
                        .width(40.dp)
                        .height(20.dp)
                ){
                    Text(text = "${gameUiState.currentWordCount}/ 10", fontWeight = FontWeight(800) )
                }
            }

            Text(text = currentScrambledWord, fontSize = 20.sp , fontWeight = FontWeight(600) )
            Text(text = "Unscramble the words using all letters")
            OutlinedTextField(
                value = userGuess,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight(),
                singleLine = true,
                onValueChange = { gameViewModel.updateUserGuess(it)},
                keyboardActions = KeyboardActions(onDone = { gameViewModel.checkUserGuess() } ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                isError = gameUiState.isGuessedWordWrong,
                label = { if(gameUiState.isGuessedWordWrong){
                    Text(text = "Wrong Guess")}
                else{
                    Text(text = "Enter Your Word")
                }},
            )
        }
        Row (modifier= Modifier.padding(16.dp)) {
            OutlinedButton(onClick = { gameViewModel.skipWord() }, Modifier.weight(1f)) {
                Text(text = "Skip")
            }

            Button(onClick = { gameViewModel.checkUserGuess() },
                Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
    if(gameUiState.isGameOver){
        FinalScoreDialog(score = gameUiState.score, onPlayAgain = { gameViewModel.resetGame() }, modifier = Modifier )
    }
}
@Composable
private fun FinalScoreDialog(
    score:Int,
    onPlayAgain:() -> Unit,
    modifier: Modifier
){
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = { },
        title = {
            if(score>120){ Text(text = "Congratulations")}
            else if(score in 81..119){ Text(text = "Good Try")}
            else{ Text(text = "Try Again")}
                },
        text = { Text(text = "You Scored: $score")},
        dismissButton = { TextButton(onClick = { activity.finish() }) {
            Text(text = "Exit")
        }},
        confirmButton = { TextButton(onClick = { onPlayAgain() }) {
            Text(text = "Play Again")
        }}
    )
}
