package com.example.expensetracker.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.ExpenseCategory
import com.example.expensetracker.intent.ExpenseIntent
import com.example.expensetracker.model.ExpenseItem
import com.example.expensetracker.intent.ExpenseViewState
import kotlinx.coroutines.launch

class ExpenseViewModel: ViewModel() {
    private val _viewState = mutableStateOf(ExpenseViewState())
    val viewState: State<ExpenseViewState> = _viewState

    private fun calculateTotalExpenses(expenses: List<ExpenseItem>, category: ExpenseCategory?): Double {
        if (category == null) {
            return expenses.sumOf { it.amount }
        }
        val filterExpenses = expenses.filter { it.category == category }
        return filterExpenses.sumOf { it.amount }
    }

    val totalExpense: Double
        get() = calculateTotalExpenses(_viewState.value.expenseList, _viewState.value.expenseCategory)

    fun handleIntent(intent: ExpenseIntent) {
        viewModelScope.launch {
            when (intent) {
                is ExpenseIntent.AddExpenseItem -> {
                    val expenses = _viewState.value.expenseList + intent.expenseItem
                    _viewState.value = _viewState.value.copy(expenseList = expenses)
                }
                is ExpenseIntent.FilterExpenseCategory -> {
                    _viewState.value = _viewState.value.copy(expenseCategory = intent.expenseCategory)
                }
            }
        }
    }
}