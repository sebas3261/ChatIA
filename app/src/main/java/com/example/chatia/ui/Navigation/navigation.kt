package com.example.chatia.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatia.ui.ViewModel.ChatViewModel
import com.example.chatia.ui.Views.ChatScreen
import com.example.chatia.ui.Views.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel:  ChatViewModel = ChatViewModel()
    
    NavHost(navController = navController, startDestination = "main") {
        composable("main"){
            MainScreen(navController)
        }
        composable("chat"){
            ChatScreen(navController, viewModel)
        }
    }
}