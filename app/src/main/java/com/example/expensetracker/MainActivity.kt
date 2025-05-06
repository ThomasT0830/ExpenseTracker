package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.expensetracker.uiscreens.ExpenseMainScreen
import com.example.expensetracker.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                ExpenseMainScreen(viewModel)
            }
        }
    }
}
