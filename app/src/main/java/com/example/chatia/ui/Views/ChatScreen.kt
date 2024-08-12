package com.example.chatia.ui.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chatia.ui.ViewModel.ViewModel

@Composable
fun ChatScreen(navController: NavController) {
    Box (
        modifier = Modifier
            .background(color = Color(0xFF5B5B5B))
    ){
        Column {
            Row(
                modifier = Modifier
                    .background(color = Color(0xFF2C2C2C))
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 20.dp)
            ) {
                Button (
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    )
                    ){
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
            Button(onClick = { navController.navigate("main") }) {
                Text(text = "Volver")
            }
        }
        Row(
            modifier = Modifier
                .background(color = Color(0xFF2C2C2C))
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.BottomEnd)
                .padding(start = 40.dp, top = 20.dp)
        ){
            ViewModel().ATextField()
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                onClick = { /*TODO*/ },
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
