package com.example.budgetSheet.expenseDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetSheet.data.DataSource
import com.example.budgetSheet.data.Expense

class ExpenseDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a expense that corresponds to an id. */
    fun getExpenseForId(id: Int) : Expense? {
        return datasource.getExpenseForId(id)
    }

    /* Queries datasource to remove a expense. */
    fun removeExpense(expense: Expense) {
        datasource.removeExpense(expense)
    }
}

class ExpenseDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}