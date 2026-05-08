package com.example.financeapp.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financeapp.data.model.model.ExpenseCategory

@Entity(tableName = "budgets")

data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val category: ExpenseCategory,
    val monthlyLimit: Double,
    val month: Int,
    val year: Int,
    val createAt: Long = System.currentTimeMillis()
)