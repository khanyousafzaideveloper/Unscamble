package com.example.unscramble

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Text(text = "Unscramble")

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val currentScrambledWord = gameUiState.currentScrambledWord
            val userGuess = gameViewModel.userGuess
            var a : Int = 0
            Text(text = "${gameUiState.currentWordCount} / 10" )
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
                }}
            )
        }
        Button(onClick = { gameViewModel.checkUserGuess() }) {
            Text(text = "Submit")
        }
        Button(onClick = { gameViewModel.skipWord() }) {
            Text(text = "Skip")
        }
        var b : Int = 0
        Text(text = "Score: $b")
    }
}