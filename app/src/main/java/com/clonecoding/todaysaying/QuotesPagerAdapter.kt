package com.clonecoding.todaysaying

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewPager 어댑터
 */
class QuotesPagerAdapter(
  private val quotes: List<Quote>,
  private val isNameRevealed: Boolean
) : RecyclerView.Adapter<QuotesPagerAdapter.QuoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quote, parent, false)
        )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

        holder.bind(quotes[position], isNameRevealed)
    }

    override fun getItemCount() = quotes.size

    /**
     * View Holder
     */
    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)

        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        @SuppressLint("SetTextI18n")
        fun bind(quote: Quote, isNameRevealed: Boolean) {

            this.quoteTextView.text = "\"${quote.quote}\""

            if(isNameRevealed) {
                this.nameTextView.text = "- ${quote.name}"
                this.nameTextView.visibility = View.VISIBLE
            } else {
                this.nameTextView.visibility = View.GONE
            }
        }
    }
}