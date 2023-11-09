package com.haiphong.algorithmsvisualizer.ui.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.haiphong.algorithmsvisualizer.data.model.Item

@Composable
fun ListItem(item: Item, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(30.dp)
            .height((item.value * 20).dp)
            .background(if (item.isHighlighted) Color.Red else Color.Blue),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = item.value.toString(),
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}