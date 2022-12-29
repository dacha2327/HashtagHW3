package com.example.hashtaghw3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hashtaghw3.databinding.ItemTextBinding

class TextAdapter(private val list: ArrayList<String>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<TextAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class MessageViewHolder(private val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(text: String) {
            binding.itemText.text = "#$text"
            itemView.setOnClickListener {
                clickListener(text)
            }

        }
    }
}