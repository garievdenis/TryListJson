package com.example.trylistjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowContactActivity : AppCompatActivity() {

    private val contactList: MutableList<Contact> = mutableListOf()
    private lateinit var rv: RecyclerView
    private var index: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)
        getContacts()
        val adapter = ContactRVAdapter(this, contactList)

        val rvListener = object : ContactRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@ShowContactActivity, AddContactActivity::class.java)
                intent.putExtra("index", position)
                index = position
                startActivity(intent)

            //Toast.makeText(this@ShowContactActivity, "position: $position", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setOnClickListener(rvListener)
        rv = findViewById(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
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

    override fun onResume() {
        super.onResume()
        if (index != -1){
            contactList.clear()
            getContacts()
            rv.adapter?.notifyItemChanged(index)
        }

    }

}