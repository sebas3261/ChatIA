package com.example.chatia.ui.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chatia.R

@Composable
fun MainScreen(navController: NavController){
    Column{
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.imagen))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)

    }
}