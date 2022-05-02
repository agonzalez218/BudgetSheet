package com.example.budgetSheet.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.budgetSheet.expenseList.expenseCount
import com.example.budgetSheet.expenseList.expenseIndicies

/* Handles operations on expensesLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialExpenseList = expenseList(resources)
    private val expensesLiveData = MutableLiveData(initialExpenseList)

    /* Adds expense to liveData and posts value. */
    fun addExpense(expense: Expense) {
        val currentList = expensesLiveData.value
        if (currentList == null) {
            expensesLiveData.postValue(listOf(expense))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, expense)
            expensesLiveData.postValue(updatedList)
        }
    }

    /* Removes flower from liveData and posts value. */
    fun removeExpense(expense: Expense) {
        val currentList = expensesLiveData.value
        expenseCount--
        expenseIndicies.remove(expense.id)
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(expense)
            expensesLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getExpenseForId(id: Int): Expense? {
        expensesLiveData.value?.let { expenses ->
            return expenses.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getExpenseList(): LiveData<List<Expense>> {
        return expensesLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}