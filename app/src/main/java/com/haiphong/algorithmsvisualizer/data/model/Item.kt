package com.haiphong.algorithmsvisualizer.data.model

private fun getItemId(): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..10).map { allowedChars.random() }.joinToString("")
}

data class Item(val value: Int, val id: String = getItemId(), val isHighlighted: Boolean = false)
