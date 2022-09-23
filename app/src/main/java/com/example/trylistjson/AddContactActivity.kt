package com.example.trylistjson

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddContactActivity : AppCompatActivity() {

    private var contactList: MutableList<Contact> = mutableListOf()

    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var button: Button
    private var index: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        getContacts()

        index = intent.getIntExtra("index", -1)



        name = findViewById(R.id.editTextTextPersonName)
        email = findViewById(R.id.editTextTextEmailAddress)
        button = findViewById(R.id.button3)

        if(index != -1){
            button.setText("Изменить контакт")
            name.setText(contactList[index].name)
            email.setText(contactList[index].email)
        }

        button.setOnClickListener {

            if (index == -1){
                addContact(name.text.toString(), email.text.toString())
                Log.d("HeyBro!", contactList.toString())
            } else {
                contactList[index].name = name.text.toString()
                contactList[index].email = email.text.toString()
                val preferences = getSharedPreferences("pref", MODE_PRIVATE)
                preferences.edit {
                    this.putString("json", Gson().toJson(contactList).toString())
                }
            }
            name.setText("")
            email.setText("")
        }
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

    private fun addContact(name:String, email:String){
        val contact = Contact(name, email)
        contactList.add(contact)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit {
            this.putString("json", Gson().toJson(contactList).toString())
        }
    }
}
