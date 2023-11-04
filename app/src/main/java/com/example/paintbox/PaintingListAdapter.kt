package com.example.paintbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paintbox.databinding.ItemRowPaintingBinding

class PaintingListAdapter(private val listPainting: ArrayList<paintings>) : RecyclerView.Adapter<PaintingListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: paintings)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowPaintingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPainting.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, maker, timeCreated, photo) = listPainting[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.paintingImageView)
        holder.binding.paintingTitleTextView.text = name
        holder.binding.paintingDescTextView.text = "created by $maker"
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPainting[holder.adapterPosition]) }

    }


    class ListViewHolder(var binding: ItemRowPaintingBinding) : RecyclerView.ViewHolder(binding.root) {}
}