package com.example.unscramble

data class GameUiState(
    val currentScrambledWord: String ="",
    val currentWordCount: Int = 1,
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0
)