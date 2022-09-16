package com.example.trylistjson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactRVAdapter(context: Context?, val data: MutableList<Contact>):RecyclerView.Adapter<ContactViewHolder?>() {
    private val layoutInflater: LayoutInflater = android.view.LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = layoutInflater.inflate(R.layout.list_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = data[position]
        holder.nameTextView.text = item.name
        holder.emailTextView.text = item.email
    }

    override fun getItemCount(): Int = data.size

/*    inner class ContactViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var nameTextView: TextView = item.findViewById(R.id.tvName)
        var emailTextView: TextView = item.findViewById(R.id.tvEmail)
    }*/

}