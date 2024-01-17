package com.example.unscramble

data class GameUiState(
    val currentScrambledword: String ="",
    val isGuessedWordWrong: Boolean = false
)