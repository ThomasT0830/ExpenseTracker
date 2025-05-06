package com.example.expensetracker.uiscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.example.expensetracker.model.ExpenseCategory
import com.example.expensetracker.intent.ExpenseIntent
import com.example.expensetracker.viewmodel.ExpenseViewModel

@Composable
fun ExpenseMainScreen(viewModel: ExpenseViewModel) {
    val viewState by viewModel.viewState

    val filteredExpenses =
        if (viewState.expenseCategory == null) viewState.expenseList
        else viewState.expenseList.filter { it.category == viewState.expenseCategory }

    var addingExpense by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)){
        Column {
            Column(
                Modifier
                    .padding(35.dp, 65.dp, 35.dp, 30.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.27F), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        "Expense Tracker",
                        color = Color.White,
                        style = TextStyle(fontSize = 24.sp),
                        fontWeight = FontWeight.Medium
                    )
                    Box(Modifier.clickable {
                        addingExpense = true
                    }) {
                        Text(
                            "+",
                            color = Color.White,
                            style = TextStyle(fontSize = 40.sp),
                            fontWeight = FontWeight.Light,
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                    Column {
                        var amountText: String = "Total Expense"
                        if (viewState.expenseCategory != null) {
                            amountText = viewState.expenseCategory!!.title + " Expense"
                        }
                        Text(
                            text = amountText,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "$" + viewModel.totalExpense.toString(),
                            color = Color.White,
                            fontSize = 70.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Box(Modifier
                .background(Color.White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .fillMaxHeight()
                .fillMaxWidth()) {
                Column(Modifier.padding(30.dp, 40.dp)){
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "Expenses",
                            color = Color.Black,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Box{
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { expanded = true }
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF0085FF),
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(20.dp, 10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Info Icon",
                                    tint = Color(0xFF0085FF),
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                if (viewState.expenseCategory != null) {
                                    Text(
                                        text = viewState.expenseCategory!!.title,
                                        color = Color(0xFF0085FF),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                else {
                                    Text(
                                        text = "Filter Category",
                                        color = Color(0xFF0085FF),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Spacer(Modifier.height(30.dp))
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                Modifier.align(Alignment.BottomEnd).background(Color(0xFF0085FF))
                            ) {
                                (listOf("All Expenses") + ExpenseCategory.entries.map { it.title }).forEach { option ->
                                    if (option != "All Expenses") {
                                        HorizontalDivider(color = Color.White)
                                    }
                                    DropdownMenuItem(
                                        text = { Text(option, color = Color.White) },
                                        onClick = {
                                            if (option != "All Expenses") {
                                                viewModel.handleIntent(
                                                    ExpenseIntent.FilterExpenseCategory(
                                                        ExpenseCategory.entries.find { it.title == option })
                                                )
                                            }
                                            else {
                                                viewModel.handleIntent(
                                                    ExpenseIntent.FilterExpenseCategory(
                                                        null
                                                    )
                                                )
                                            }
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFF0085FF), thickness = 3.dp)
                    LazyColumn() {
                        items(filteredExpenses.reversed()) { expense ->
                            ExpenseItemDisplay(expense)
                        }
                    }
                }
            }
            if (addingExpense) {
                 ExpenseInputDialog(
                     onDismiss = { addingExpense = false },
                     onSubmit = {
                         viewModel.handleIntent(ExpenseIntent.AddExpenseItem(it))
                         addingExpense = false
                     }
                 )
            }
        }
    }
}


