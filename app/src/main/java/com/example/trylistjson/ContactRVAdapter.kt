package com.example.trylistjson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactRVAdapter(context: Context?, val data:MutableList<Contact>) : RecyclerView.Adapter<ContactViewHolder?>(){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = layoutInflater.inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = data[position]
        holder.nameTextView.text = item.name
        holder.emailTextView.text = item.email
    }

    override fun getItemCount(): Int = data.size

}