package com.example.proiectsma

import androidx.compose.ui.graphics.Color

enum class AssignmentPriority(val priorityType: String, val priorityColor: Color, val priorityValue: Int) {
    PRIMARY(priorityType = "Primary", priorityColor = Color(0xFFFF8B00), priorityValue = 1),
    SECONDARY(priorityType = "Secondary", priorityColor = Color.Gray, priorityValue = 2);

    companion object {
        fun priority(priorityValue: Int) =
            values().firstOrNull{it.priorityValue == priorityValue} ?: SECONDARY
    }
}