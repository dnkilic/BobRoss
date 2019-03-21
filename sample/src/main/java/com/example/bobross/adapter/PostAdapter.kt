package com.example.bobross.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bobross.repository.model.Post
import com.example.bobross.databinding.ItemPostBinding

class PostAdapter(private val items: List<Post>, private val itemClickListener: (Post, Int) -> Unit)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], position, itemClickListener)

    inner class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Post,
            position: Int,
            itemClickListener: (Post, Int) -> Unit
        ) {
            binding.item = item
            binding.favorite.setOnClickListener{
                (itemClickListener)(item, position)
            }
            binding.executePendingBindings()
        }
    }
}