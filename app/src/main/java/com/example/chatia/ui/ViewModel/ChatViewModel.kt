package com.example.chatia.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

data class Message(
    val text: String,
    val sender: String,
    val date: Date,
    val isSeen: Boolean = false
)

data class ChatUIState(
    var message: MutableList<Message> = mutableListOf()
)

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUIState())
    val uiState: StateFlow<ChatUIState> = _uiState.asStateFlow()

    var currentMessage by mutableStateOf("")
        private set

    fun updateCurrentMessage(text: String){
        currentMessage = text
    }



}