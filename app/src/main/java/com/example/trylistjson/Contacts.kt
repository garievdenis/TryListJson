package com.example.trylistjson

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Contacts {
    fun getContacts(preferences: SharedPreferences): MutableList<Contact>{
        var json: String = ""
        if (!preferences.contains("json")){
            return mutableListOf()
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<MutableList<Contact>>(json, object: TypeToken<MutableList<Contact>>(){}.type)
        return tempList
    }
    fun addContact(name:String, email:String, preferences:SharedPreferences){
        val contact = Contact(name, email)
        var temp = this.getContacts(preferences)
        temp.add(contact)
        preferences.edit {
            this.putString("json", Gson().toJson(temp).toString())
        }
    }
    fun saveContacts(list: MutableList<Contact>, preferences: SharedPreferences){
        preferences.edit {
            this.putString("json", Gson().toJson(list).toString())
        }
    }
}