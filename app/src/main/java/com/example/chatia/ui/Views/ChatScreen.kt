package com.example.chatia.ui.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chatia.ui.ViewModel.ChatViewModel
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(navController: NavController, chatViewModel: ChatViewModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val uiState by chatViewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerContent = {
            Text(text = "Drawer content")
            Button(onClick = { navController.navigate("main") }) {
                Text("volver")
            }
        },
        drawerState = drawerState
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF5B5B5B))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color(0xFF2C2C2C))
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 20.dp)
                ) {
                    Button(
                        onClick = { scope.launch { drawerState.open() } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2C2C2C)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(60.dp)
                        )
                    }
                    Text(
                        text = "ChatIA",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                            .padding(start = 70.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    val listState = rememberLazyListState()
                    LaunchedEffect(uiState.messages.size) {
                        listState.animateScrollToItem(uiState.messages.size)
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(uiState.messages) { message ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .padding(horizontal = if (message.sender == "me") 64.dp else 8.dp),
                                horizontalArrangement = if (message.sender == "me") Arrangement.End else Arrangement.Start
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = if (message.sender == "me") Color(0xFF00796B) else Color(0xFF4CAF50),
                                            shape = MaterialTheme.shapes.medium
                                        )
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = message.text,
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Input para escribir y enviar mensajes, alineado al fondo
            Row(
                modifier = Modifier
                    .background(color = Color(0xFF2C2C2C))
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Say something...", color = Color.White) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        chatViewModel.updateCurrentMessage(text)
                        chatViewModel.sendMessage("me") // Simulando un remitente
                        text = "" // Limpiar el campo de texto
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
