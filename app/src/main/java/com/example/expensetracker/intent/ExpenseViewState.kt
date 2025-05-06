package com.example.expensetracker.intent

import com.example.expensetracker.model.ExpenseCategory
import com.example.expensetracker.model.ExpenseItem

data class ExpenseViewState(
    val expenseList: List<ExpenseItem> = emptyList(),
    val expenseCategory: ExpenseCategory? = null
)