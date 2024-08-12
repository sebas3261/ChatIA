package com.example.chatia.ui.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*

class ViewModel : ViewModel() {
    @Composable
    fun ATextField(){
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,onValueChange = { text = it },
            label = { Text("Say something...") },
        )
    }
}