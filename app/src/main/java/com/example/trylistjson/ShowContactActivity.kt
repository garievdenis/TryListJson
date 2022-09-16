package com.example.trylistjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowContactActivity : AppCompatActivity() {

    private val contactList: MutableList<Contact> = mutableListOf()
    private lateinit var rv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)
        getContacts()
        rv = findViewById(R.id.recycleView)
        val adapter = ContactRVAdapter(this, contactList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

    }

    private fun getContacts(){
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<Contact>>(json, object: TypeToken<List<Contact>>(){}.type)
        contactList.addAll(tempList)
    }
}