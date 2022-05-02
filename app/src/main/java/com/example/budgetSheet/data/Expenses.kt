@file:JvmName("ExpenseKt")

package com.example.budgetSheet.data

import android.content.res.Resources

/* Returns initial list of flowers. */
fun expenseList(resources: Resources): List<Expense> {
    return listOf(
        Expense(
            id = 1,
            cost = "$80",
            category = "Groceries"
        ),
        Expense(
            id = 2,
            cost = "$20",
            category = "Eating Out"
        )
    )
}