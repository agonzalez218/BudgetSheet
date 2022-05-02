package com.example.budgetSheet.expenseStats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.example.budgetSheet.R
import com.example.budgetSheet.expenseDetail.ExpenseDetailViewModel
import com.example.budgetSheet.expenseDetail.ExpenseDetailViewModelFactory
import com.example.budgetSheet.expenseList.expenseIndicies

class ExpenseStats : AppCompatActivity() {

    private val expenseDetailViewModel by viewModels<ExpenseDetailViewModel> {
        ExpenseDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_stats)
        var entertainmentCost = 0
        var eatingOutCost = 0
        var clothesCost = 0
        var groceriesCost = 0
        var currentElementIndex =  1
        var string = "Error"
        var currentCost = 0
        var currentCat = ""

        val statsText = findViewById<TextView>(R.id.stats_text)
        for(currentElementIndex in expenseIndicies)
        {
            println(currentElementIndex)
            currentElementIndex.let {
                val currentElement = expenseDetailViewModel.getExpenseForId(it)
                currentCost = currentElement?.cost?.replace("$", "")!!.toInt()
                currentCat = currentElement.category
            }

            when (currentCat) {
                "Groceries" -> groceriesCost += currentCost
                "Eating Out" -> eatingOutCost += currentCost
                "Entertainment" -> entertainmentCost += currentCost
                "Clothes" -> clothesCost += currentCost
                else -> println("Error")
            }
        }


        string = "Total Cost of Groceries = $${groceriesCost}\n\n" +
                "Total Cost of Eating Out = $${eatingOutCost}\n\n" +
                "Total Cost of Clothes = $${clothesCost}\n\n" +
                "Total Cost of Entertainment = $${entertainmentCost}\n"

        statsText.text = string
    }

    fun backFun(view: View) {
        this.onBackPressed()
    }
}