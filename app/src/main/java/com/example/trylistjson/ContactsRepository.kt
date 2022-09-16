package com.example.trylistjson

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContactsRepository private constructor(preferences: SharedPreferences){

    val contactList = createContactList(preferences)

    private fun createContactList(preferences: SharedPreferences): MutableList<Contact>{
        var json: String = ""
        val result = mutableListOf<Contact>()
        if (!preferences.contains("json")){
            return mutableListOf()
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<Contact>>(json, object: TypeToken<List<Contact>>(){}.type)
        result.addAll(tempList)
        return result
    }
    companion object{
        private var INSTANCE:ContactsRepository? = null
        fun initialize(preferences: SharedPreferences){
            if (INSTANCE == null){
                INSTANCE = ContactsRepository(preferences)
            }
        }
        fun get(): ContactsRepository{
            return INSTANCE ?:
            throw IllegalStateException("Repository should be initialized!")
        }
    }
}