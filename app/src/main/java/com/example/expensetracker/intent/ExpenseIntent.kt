package com.example.expensetracker.intent

import com.example.expensetracker.model.ExpenseCategory
import com.example.expensetracker.model.ExpenseItem

sealed class ExpenseIntent {
    data class AddExpenseItem(val expenseItem: ExpenseItem): ExpenseIntent()
    data class FilterExpenseCategory(val expenseCategory: ExpenseCategory?): ExpenseIntent()
}