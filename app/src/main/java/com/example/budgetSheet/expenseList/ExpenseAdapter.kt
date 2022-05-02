package com.example.budgetSheet.expenseList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetSheet.R
import com.example.budgetSheet.data.Expense

class ExpensesAdapter(private val onClick: (Expense) -> Unit) :
    ListAdapter<Expense, ExpensesAdapter.ExpenseViewHolder>(ExpenseDiffCallback) {

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class ExpenseViewHolder(itemView: View, val onClick: (Expense) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val expenseTextView: TextView = itemView.findViewById(R.id.flower_text)
        private var currentExpense: Expense? = null

        init {
            itemView.setOnClickListener {
                currentExpense?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(expense: Expense) {
            currentExpense = expense

            expenseTextView.text = expense.cost.toString()
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view, onClick)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = getItem(position)
        holder.bind(expense)
    }
}

object ExpenseDiffCallback : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem.id == newItem.id
    }
}