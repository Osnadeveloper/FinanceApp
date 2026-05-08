package com.example.financeapp.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financeapp.data.model.model.ExpenseCategory

@Entity(tableName = "expenses")

data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val emount: Double,
    val category: ExpenseCategory,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    val isRecurring: Boolean = false
)