package com.example.unscramble

import android.app.Activity
import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
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
        Text(text = "Unscramble", fontFamily = FontFamily.SansSerif)

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            val currentScrambledWord = gameUiState.currentScrambledWord
            val userGuess = gameViewModel.userGuess
            Row {
                var b : Int = 0
                Text(text = "Score: $b")
                Text(text = "${gameUiState.currentWordCount}/ 10" )
            }

            Text(text = currentScrambledWord)
            Text(text = "Unscramble the words using all letters")
            OutlinedTextField(
                value = userGuess,
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
        Row(modifier = Modifier) {
            Button(onClick = { gameViewModel.skipWord() }) {
                Text(text = "Skip")
            }
            Button(onClick = { gameViewModel.checkUserGuess() }) {
                Text(text = "Submit")
                Modifier.background(Color.LightGray)
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
        onDismissRequest = {  },
        title = { Text(text = "Congratulations")},
        text = { Text(text = "You Scored: $score")},
        dismissButton = { TextButton(onClick = { activity.finish() }) {
            Text(text = "Exit")
        }},
        confirmButton = { TextButton(onClick = { onPlayAgain() }) {
            Text(text = "Play Again")
        }}
    )
}
