package com.haiphong.algorithmsvisualizer.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.haiphong.algorithmsvisualizer.data.model.Item
import com.haiphong.algorithmsvisualizer.ui.composables.ListItem
import com.haiphong.algorithmsvisualizer.ui.composables.MyButton
import com.haiphong.algorithmsvisualizer.ui.viewmodels.MainViewModel
import com.haiphong.algorithmsvisualizer.ui.viewmodels.SortingAlgorithm
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val uiState by mainViewModel.uiState.collectAsState()

    var index1 by remember {
        mutableStateOf("")
    }

    var index2 by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            items(uiState.list, key = { item -> item.id }) { item ->
                ListItem(
                    item = item, modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 1000,
                            delayMillis = 50,
                            easing = LinearEasing
                        )
                    )
                )

            }
        }
        Divider(modifier = Modifier.height(5.dp))
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }) {
                TextField(
                    value = uiState.sortingAlgorithm.name,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    SortingAlgorithm.values().forEach {
                        DropdownMenuItem(text = { Text(text = it.name) }, onClick = {
                            mainViewModel.changeSortingAlgorithm(it)
                            expanded = false
                        })
                    }
                }
            }

            MyButton(text = "Sort", enabled = uiState.sortingAlgorithm != SortingAlgorithm.None) {
                mainViewModel.sort(uiState.sortingAlgorithm)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        MyButton(text = "Reset List") {
            mainViewModel.resetList()
        }
        Spacer(modifier = Modifier.height(20.dp))
        MyButton(text = "Shuffle List") {
            mainViewModel.shuffleList()
        }
    }
}

/*
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.weight(1f),
                value = index1,
                onValueChange = { newString -> index1 = newString })
            Spacer(modifier = Modifier.width(5.dp))
            TextField(
                modifier = Modifier.weight(1f),
                value = index2,
                onValueChange = { newString -> index2 = newString })
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                modifier = Modifier.weight(1.0F),
                onClick = { mainViewModel.swap(index1.toInt(), index2.toInt()) }) {
                Text(text = "Swap")
            }
        }

         */