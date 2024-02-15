package org.example.kmpsample.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import domain.model.SearchRecentQuery
import org.example.kmpsample.R
import org.example.kmpsample.databinding.ItemSearchRecentQueryBinding

class SearchRecentQueryAdapter :
    ListAdapter<SearchRecentQuery, SearchRecentQueryAdapter.SearchRecentQueryViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecentQueryViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_search_recent_query, parent, false)
        return SearchRecentQueryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchRecentQueryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchRecentQueryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemSearchRecentQueryBinding.bind(itemView)

        fun bind(item: SearchRecentQuery) {
            with(binding) {
                searchRecentQueryTextLabel.text = item.text
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchRecentQuery>() {
            override fun areItemsTheSame(
                oldItem: SearchRecentQuery,
                newItem: SearchRecentQuery
            ): Boolean {
                return oldItem.text == newItem.text
            }

            override fun areContentsTheSame(
                oldItem: SearchRecentQuery,
                newItem: SearchRecentQuery
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}