package com.example.trylistjson

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class ContactsApp: Application(){
    override fun onCreate() {
        super.onCreate()
        ContactsRepository.initialize(getSharedPreferences("pref", MODE_PRIVATE))
    }
}