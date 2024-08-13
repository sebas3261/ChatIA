package com.example.chatia.ui.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chatia.R
import com.example.chatia.ui.Navigation.Navigation
import com.example.chatia.ui.theme.ChatIATheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun MainScreen(navController: NavController){
    Box(
        modifier = Modifier
            .offset(y = 30.dp)
    ){
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.imagen))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    color = Color(0xFF2C2C2C),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .align(Alignment.BottomEnd)
                .padding(30.dp)
        ){
            Button(
                onClick = { navController.navigate("chat") },
                modifier = Modifier
                    .width(500.dp)
                    .height(50.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White  // Color de fondo del botón
                )
            ){
                Text(
                    "Log in",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = { println("pos no") },
                modifier = Modifier
                    .width(500.dp)
                    .height(50.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF51BD9C)  // Color de fondo del botón
                )
            ){
                Text(
                    "Sign Up",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}