@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.example.financeapp.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao

interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Query(value = "SELECT * FROM expenses WHERE userId = :userId ORDER BY date DESC")
    fun getExpensesByUser(userId:Long): Flow<List<Expense>>

    @Query(value = "SELECT * FROM expenses WHERE userId = :userId AND category = :category ORDER BY date DESC")
    fun getExpensesByUser(userId:Long, category: ExpenseCategory): Flow<List<Expense>>

    @Query(value = "SELECT * FROM expenses WHERE userId = :userId AND date BETWEEN :startDate  AND :endDate ORDER BY date DESC")
    fun getExpensesByDateRange(userId: Long, startDate: Long, endDate: Long): Flow<List<Expense>>

    @Query(value = "SELECT SUM(amount) FROM expenses WHERE userId= :userId AND date BETWEEN :startDate AND :endDate")
    fun getTotalExpensesByDateRange(userId: Long, startDate: Long, endDate: Long): Flow<Double>

    @Query(value = "SELECT SUM(amount) FROM expenses WHERE userId= :userId AND category = :category AND date BETWEEN :startDate AND :endDate")
    fun etTotalExpensesByCategoryAndDateRange(
        userId: Long,
        category: ExpenseCategory,
        startDate: Long,
        endDate: Long,
    ): Flow<Double>

    @Update fun update(expense: Expense)

    @Delete
    suspend fun delate(expense: Expense)




}