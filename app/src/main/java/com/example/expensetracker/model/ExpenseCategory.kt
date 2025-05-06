package com.example.expensetracker.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class ExpenseCategory(val title: String, val color: Color, val icon: ImageVector) {
    FOOD("Food", Color(0xFFFF0085), Icons.Default.Star),
    TRAVEL("Travel", Color(0xFF00c100), Icons.Default.Place),
    UTILITIES("Utilities", Color(0xFFffa000), Icons.Default.ShoppingCart);
}
