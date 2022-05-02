package com.example.budgetSheet.expenseDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetSheet.R
import com.example.budgetSheet.expenseList.EXPENSE_ID
import com.example.budgetSheet.expenseList.ExpenseListViewModel

class ExpenseDetailActivity : AppCompatActivity() {

    private val expenseDetailViewModel by viewModels<ExpenseDetailViewModel> {
        ExpenseDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense_detail_activity)

        var currentExpenseId: Int? = null

        /* Connect variables to UI elements. */
        val expenseName: TextView = findViewById(R.id.expense_cost)
        val expenseDescription: TextView = findViewById(R.id.expense_category)
        val removeExpenseButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentExpenseId = bundle.getInt(EXPENSE_ID)
        }

        /* If currentExpenseId is not null, get corresponding flower and set name, image and
        description */
        currentExpenseId?.let {
            val currentExpense = expenseDetailViewModel.getExpenseForId(it)
            expenseName.text = currentExpense?.cost.toString()
            expenseDescription.text = currentExpense?.category

            removeExpenseButton.setOnClickListener {
                if (currentExpense != null) {
                    expenseDetailViewModel.removeExpense(currentExpense)
                }
                finish()
            }
        }

    }
    fun backFun(view: View) {
        this.onBackPressed()
    }
}