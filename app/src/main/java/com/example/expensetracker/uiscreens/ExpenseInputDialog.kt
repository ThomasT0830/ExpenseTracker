package com.example.expensetracker.uiscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.expensetracker.model.ExpenseCategory
import com.example.expensetracker.model.ExpenseItem

@Composable
fun ExpenseInputDialog(onDismiss: () -> Unit, onSubmit: (ExpenseItem) -> Unit){
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(ExpenseCategory.FOOD) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(30.dp, 40.dp, 30.dp, 35.dp),
            ){
                Text(
                    text = "Add Expense",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Blue,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Blue,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.DarkGray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                    )
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = {
                        if (it.all { ch -> ch.isDigit() || ch == '.' }) amount = it
                    },
                    label = { Text("Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Blue,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Blue,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.DarkGray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text("Category:")
                Spacer(Modifier.height(5.dp))
                ExpenseCategory.entries.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        RadioButton(
                            selected = category == it,
                            onClick = { category = it },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Blue,
                                unselectedColor = Color.Gray,
                                disabledSelectedColor = Color.LightGray,
                                disabledUnselectedColor = Color.LightGray
                            )
                        )
                        Text(it.title)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel", color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        val parsedAmount = amount.toDoubleOrNull()
                        if (!name.isBlank() && parsedAmount != null) {
                            val expense = ExpenseItem(
                                name = name,
                                amount = parsedAmount,
                                category = category
                            )
                            onSubmit(expense)
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,       // Background color
                        contentColor = Color.White         // Text/icon color
                    )) {
                        Text("Add")
                    }
                }
            }
        }
    }
}