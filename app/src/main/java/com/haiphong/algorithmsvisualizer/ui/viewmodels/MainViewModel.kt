package com.haiphong.algorithmsvisualizer.ui.viewmodels

import androidx.compose.foundation.layout.Box
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haiphong.algorithmsvisualizer.data.model.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val LIST_SIZE = 10
private const val RANGE_FROM = 1
private const val RANGE_TO = 21

enum class SortingAlgorithm {
    InsertionSort,
    BubbleSort,
    SelectionSort,
    QuickSort,
    None
}

data class UiState(
    val list: List<Item> = listOf(),
    val sortingAlgorithm: SortingAlgorithm = SortingAlgorithm.None
)

class MainViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                list = getRandomList()
            )
        }
    }

    fun changeSortingAlgorithm(newSortingAlgorithm: SortingAlgorithm) {
        _uiState.update {
            it.copy(
                sortingAlgorithm = newSortingAlgorithm
            )
        }
    }

    fun resetList() {
        _uiState.update {
            it.copy(
                list = getRandomList()
            )
        }
    }

    fun swap(index1: Int, index2: Int) {
        val newList = _uiState.value.list.map { it }.toMutableList()
        newList[index1] = _uiState.value.list[index2]
        newList[index2] = _uiState.value.list[index1]
        _uiState.update {
            it.copy(
                list = newList
            )
        }
    }

    private fun removeHighlights() {
        val newList =
            _uiState.value.list.map { item ->
                if (item.isHighlighted) item.copy(isHighlighted = false) else item
            }
        _uiState.update {
            it.copy(
                list = newList
            )
        }
    }

    private fun highlightItems(index1: Int, index2: Int) {
        val newList =
            _uiState.value.list.mapIndexed { index, item ->
                if (index == index1 || index == index2) item.copy(isHighlighted = true) else item
            }
        _uiState.update {
            it.copy(
                list = newList
            )
        }
    }

    fun shuffleList() {
        _uiState.update {
            it.copy(
                list = _uiState.value.list.shuffled()
            )
        }
    }

    private fun getRandomList(): List<Item> {
        return List(LIST_SIZE) { Item(value = Random.nextInt(RANGE_FROM, RANGE_TO)) }
    }

    fun sort(sortingAlgorithm: SortingAlgorithm) {
        viewModelScope.launch {
            when (sortingAlgorithm) {
                SortingAlgorithm.InsertionSort -> insertionSort()
                SortingAlgorithm.BubbleSort -> bubbleSort()
                SortingAlgorithm.SelectionSort -> selectionSort()
                SortingAlgorithm.QuickSort -> quickSort(0, uiState.value.list.size - 1)
                else -> {}
            }
        }
    }

    private suspend fun insertionSort() {
        for (i in 1 until _uiState.value.list.size) {
            val key = _uiState.value.list[i].value
            var j = i - 1
            highlightItems(i, j)
            delay(750)
            removeHighlights()
            while (j >= 0 && _uiState.value.list[j].value > key) {
                highlightItems(j, j + 1)
                swap(j, j + 1)
                delay(1500)
                j--
                removeHighlights()
            }
        }
    }

    private suspend fun bubbleSort() {
        var swapped: Boolean
        for (i in 0.._uiState.value.list.size - 2) {
            swapped = false
            for (j in 0.._uiState.value.list.size - 2 - i) {
                if (_uiState.value.list[j].value > _uiState.value.list[j + 1].value) {
                    highlightItems(j, j + 1)
                    swap(j, j + 1)
                    delay(1500)
                    removeHighlights()
                    swapped = true
                } else {
                    highlightItems(j, j + 1)
                    delay(750)
                    removeHighlights()
                }

            }

            if (!swapped) break
        }
    }

    //maybe turn highlight function's parameter into varargs to be able to highlight 3 items
    private suspend fun selectionSort() {
        val size = _uiState.value.list.size
        for (i in 0..size - 2) {
            var minIndex = i
            for (j in i + 1 until size) {
                highlightItems(minIndex, j)
                if (_uiState.value.list[j].value < _uiState.value.list[minIndex].value) {
                    minIndex = j
                }
                delay(750)
                removeHighlights()
            }
            highlightItems(i, minIndex)
            swap(i, minIndex)
            delay(1500)
            removeHighlights()
        }
    }

    private suspend fun partition(low: Int, high: Int): Int {
        val pivot = uiState.value.list[high].value

        var i = low - 1

        for (j in low until high) {
            if (uiState.value.list[j].value <= pivot) {
                highlightItems(j, high)
                delay(500)
                i++
                highlightItems(i, j)
                swap(i, j)
                delay(1500)
                removeHighlights()
            } else {
                highlightItems(j, high)
                delay(750)
                removeHighlights()
            }
        }

        highlightItems(i + 1, high)
        swap(i + 1, high)
        delay(1500)
        removeHighlights()

        return i + 1
    }

    private suspend fun quickSort(low: Int, high: Int) {
        if (low < high) {
            val pi = partition(low, high)

            quickSort(low, pi - 1)
            quickSort(pi + 1, high)
        }
    }
}