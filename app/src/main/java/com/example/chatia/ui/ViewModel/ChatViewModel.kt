package com.example.chatia.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.chatia.data.ChatResponse
import com.example.chatia.data.GeminiApiServiceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

data class Message(
    val text: String,
    val sender: String,
    val date: Date,
    val isSeen: Boolean = false
)

data class ChatUIState(
    var messages: List<Message> = emptyList()
)

class ChatViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUIState())
    val uiState: StateFlow<ChatUIState> = _uiState.asStateFlow()

    var currentMessage by mutableStateOf("")
        private set

    private val geminiApiServiceHelper = GeminiApiServiceHelper()

    fun updateCurrentMessage(text: String) {
        currentMessage = text
    }

    fun sendMessage(sender: String) {
        if (currentMessage.isBlank()) return

        val newMessage = Message(
            text = currentMessage,
            sender = sender,
            date = Date()
        )

        _uiState.value = _uiState.value.copy(
            messages = _uiState.value.messages + newMessage
        )

        // Enviar el mensaje al modelo de Gemini
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    geminiApiServiceHelper.sendMessageToGemini(currentMessage).execute()
                }

                if (response.isSuccessful) {
                    val geminiResponse: ChatResponse? = response.body()
                    val reply = geminiResponse?.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text

                    if (reply != null) {
                        val botMessage = Message(
                            text = reply,
                            sender = "bot",
                            date = Date()
                        )

                        _uiState.value = _uiState.value.copy(
                            messages = _uiState.value.messages + botMessage
                        )
                    } else {
                        // Manejo de caso en que no haya respuesta
                        println("No reply received from Gemini.")
                    }
                } else {
                    // Manejo de error
                    println("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Failed to connect: ${e.message}")
            } finally {
                // Limpia el mensaje actual después de intentar enviar
                currentMessage = ""
            }
        }
    }
}
