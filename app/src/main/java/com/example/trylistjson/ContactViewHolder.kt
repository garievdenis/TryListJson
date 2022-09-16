package com.example.trylistjson

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView:TextView = itemView.findViewById(R.id.tvName)
    var emailTextView: TextView = itemView.findViewById(R.id.tvEmail)
}