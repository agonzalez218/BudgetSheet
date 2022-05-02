package com.example.budgetSheet.addExpense

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.budgetSheet.R
import com.google.android.material.textfield.TextInputEditText

const val EXPENSE_NAME = "name"
const val EXPENSE_DESCRIPTION = "description"

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var addExpenseCost: TextInputEditText
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_expense_layout)

        val categories = resources.getStringArray(R.array.categories)

        spinner = findViewById<Spinner>(R.id.edit_expense_category)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, categories
        )
        spinner.adapter = adapter

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addExpense()
        }
        addExpenseCost = findViewById(R.id.edit_expense_cost)
        spinner = findViewById(R.id.edit_expense_category)
    }

    /* The onClick action for the done button. Closes the activity and returns the new expense name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */
    private fun addExpense() {
        val resultIntent = Intent()
        val expenseCostString = addExpenseCost.text.toString().substring(1)
        println(expenseCostString)
        if ((!isNumericToX(expenseCostString)) || addExpenseCost.text.toString().length < 2) {
            setResult(RESULT_CANCELED, resultIntent)
        } else {
            val name = addExpenseCost.text.toString()
            val description = spinner.selectedItem.toString()
            resultIntent.putExtra(EXPENSE_NAME, name)
            resultIntent.putExtra(EXPENSE_DESCRIPTION, description)
            setResult(RESULT_OK, resultIntent)
        }
        finish()
    }

    private fun isNumericToX(toCheck: String): Boolean {
        return toCheck.toDoubleOrNull() != null
    }
}