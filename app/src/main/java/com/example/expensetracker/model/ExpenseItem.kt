package com.example.expensetracker.model

data class ExpenseItem(
    val name: String,
    val amount: Double,
    val category: ExpenseCategory
)
