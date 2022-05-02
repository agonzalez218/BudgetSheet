package com.example.budgetSheet.expenseList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetSheet.data.DataSource
import com.example.budgetSheet.data.Expense

class ExpenseListViewModel(val dataSource: DataSource) : ViewModel() {

    val expensesLiveData = dataSource.getExpenseList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertExpense(expenseCost: String?, expenseCategory: String?) {
        if (expenseCost == null || expenseCategory == null) {
            return
        }

        val newExpense = Expense(
            expenseCount +1,
            expenseCost,
            expenseCategory
        )
        expenseIndicies += newExpense.id
        dataSource.addExpense(newExpense)
    }
}

class ExpensesListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}