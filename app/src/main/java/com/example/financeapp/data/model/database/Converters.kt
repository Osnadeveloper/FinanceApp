package com.example.financeapp.data.model.database

import androidx.room.TypeConverters
import com.example.financeapp.data.model.model.ExpenseCategory


class Converters {

    @TypeConverters
    fun fromExpenseCategory(category: ExpenseCategory): String{
        return category.name
    }

    @TypeConverters
    fun toExpenseCategory(value: String) : ExpenseCategory{
        return ExpenseCategory.valueOf(value)
    }
}