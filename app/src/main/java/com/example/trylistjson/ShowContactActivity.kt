package com.example.trylistjson

import android.content.Intent
import android.content.pm.LabeledIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowContactActivity : AppCompatActivity() {

    private val contactList: MutableList<Contact> = mutableListOf()
    private lateinit var rv: RecyclerView
    private var indexChanged: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)
        Log.d("sdasdf", "Create!")
        contactList.addAll(Contacts().getContacts(getSharedPreferences("pref", MODE_PRIVATE)))
        val adapter = ContactRVAdapter(this, contactList)
        val rvListener = object : ContactRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@ShowContactActivity, AddContactActivity::class.java)
                intent.putExtra("num", position)
                indexChanged = position
                startActivity(intent)
                Toast.makeText(this@ShowContactActivity, "position: $position", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setClickListener(rvListener)
        rv = findViewById(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        contactList.clear()
        contactList.addAll(Contacts().getContacts(getSharedPreferences("pref", MODE_PRIVATE)))
        Log.d("sdasdf", "Work It!")
        if(indexChanged != -1){
            rv.adapter?.notifyItemChanged(indexChanged)
        }
    }



}