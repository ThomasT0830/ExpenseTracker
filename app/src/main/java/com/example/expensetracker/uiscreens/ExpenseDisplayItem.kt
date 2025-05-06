package com.example.expensetracker.uiscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetracker.model.ExpenseItem

@Composable
fun ExpenseItemDisplay(expense: ExpenseItem) {
    Box(Modifier.padding(0.dp, 30.dp)){
        Row(
            Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20))
                        .background(expense.category.color)
                        .padding(15.dp, 15.dp)
                ) {
                    Icon(
                        imageVector = expense.category.icon,
                        contentDescription = "Info Icon",
                        tint = Color.White,
                    )
                }
                Spacer(Modifier.width(20.dp))
                Column {
                    Text(
                        text = expense.name,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = expense.category.title,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Box {
                Text(
                    "$" + expense.amount.toString(),
                    color = expense.category.color,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
    HorizontalDivider()
}