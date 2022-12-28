package com.malik.readcircle.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malik.readcircle.components.ReaderLogo
import com.malik.readcircle.navigation.ReaderScreens
import kotlinx.coroutines.delay


@Composable

fun ReaderSplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0.1f)
    }
    LaunchedEffect(key1 = true) {

        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween<Float>(durationMillis = 900, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            }))

        delay(2000L)

        navController.navigate(ReaderScreens.LoginScreen.name)
    }


    Surface(
        modifier = Modifier
            .padding(16.dp)
            .size(300.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ReaderLogo()
            Text(
                text = "Join a Circle, Read More",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )

        }

    }

}

