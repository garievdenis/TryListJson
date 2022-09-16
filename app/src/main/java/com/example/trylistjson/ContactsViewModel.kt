package com.example.trylistjson

import androidx.lifecycle.ViewModel

class ContactsViewModel:ViewModel() {
    private val repository = ContactsRepository.get()
    val contactList = repository.contactList

}