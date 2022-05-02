package com.example.budgetSheet.expenseList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetSheet.expenseStats.ExpenseStats
import com.example.budgetSheet.R
import com.example.budgetSheet.addExpense.AddExpenseActivity
import com.example.budgetSheet.addExpense.EXPENSE_DESCRIPTION
import com.example.budgetSheet.addExpense.EXPENSE_NAME
import com.example.budgetSheet.data.Expense
import com.example.budgetSheet.expenseDetail.ExpenseDetailActivity

const val EXPENSE_ID = "expense id"

class FlowersListActivity : AppCompatActivity() {
    private val newExpenseActivityRequestCode = 1
    private val expensesListViewModel by viewModels<ExpenseListViewModel> {
        ExpensesListViewModelFactory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* Instantiates headerAdapter and flowersAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val expensesAdapter = ExpensesAdapter { flower -> adapterOnClick(flower) }
        val concatAdapter = ConcatAdapter(headerAdapter, expensesAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        expensesListViewModel.expensesLiveData.observe(this) {
            it?.let {
                expensesAdapter.submitList(it as MutableList<Expense>)
                headerAdapter.updateFlowerCount(it.size)
            }
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
        val fab2: View = findViewById(R.id.floatingActionButton)
        fab2.setOnClickListener {
            fab2OnClick()
        }
    }

    /* Opens ExpenseDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(expense: Expense) {
        val intent = Intent(this, ExpenseDetailActivity()::class.java)
        intent.putExtra(EXPENSE_ID, expense.id)
        startActivity(intent)
    }

    /* Adds flower to flowerList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddExpenseActivity::class.java)
        startActivityForResult(intent, newExpenseActivityRequestCode)
    }

    private fun fab2OnClick() {
        val intent = Intent(this, ExpenseStats::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts expense into viewModel. */
        if (requestCode == newExpenseActivityRequestCode && resultCode == RESULT_OK) {
            intentData?.let { data ->
                val flowerName = data.getStringExtra(EXPENSE_NAME)
                val flowerDescription = data.getStringExtra(EXPENSE_DESCRIPTION)

                expensesListViewModel.insertExpense(flowerName, flowerDescription)
            }
        }
    }
}
