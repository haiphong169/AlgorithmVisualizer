package com.haiphong.algorithmsvisualizer.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TestAnimationScreen() {
//    var isVisible by remember {
//        mutableStateOf(false)
//    }

    var isRound by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { isRound = !isRound }) {
            Text(text = "Toggle")
        }

//        val borderRadius by animateIntAsState(
//            targetValue = if (isRound) 40 else 10,
//            label = "",
//            animationSpec = spring(
//                dampingRatio = Spring.DampingRatioHighBouncy,
//                stiffness = Spring.StiffnessLow
//            )
//        )

//        val transition = rememberInfiniteTransition(label = "")
//        val color by transition.animateColor(
//            initialValue = Color.Red,
//            targetValue = Color.Blue,
//            animationSpec = infiniteRepeatable(
//                animation = tween(2000),
//                repeatMode = RepeatMode.Reverse
//            ), label = "color"
//        )

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
        )
//        AnimatedVisibility(visible = isVisible, enter = slideInHorizontally() + fadeIn(), modifier = Modifier.fillMaxSize()) {
//            Box(
//                modifier = Modifier
//                    .border(5.dp, Color.DarkGray).background(Color.LightGray)
//            )
//        }
    }
}