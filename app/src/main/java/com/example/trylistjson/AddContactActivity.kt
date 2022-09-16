package com.example.trylistjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddContactActivity : AppCompatActivity() {

    private var contactList: MutableList<Contact> = mutableListOf()

    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var button: Button
    private var index:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        contactList.addAll(Contacts().getContacts(getSharedPreferences("pref", MODE_PRIVATE)))

        name = findViewById(R.id.editTextTextPersonName)
        email = findViewById(R.id.editTextTextEmailAddress)
        button = findViewById(R.id.button3)
        index = intent.getIntExtra("num", -1)
        if(index >= 0){
            name.setText(contactList[index].name)
            email.setText(contactList[index].email)
            button.setText("Изменить контакт")
        }


        button.setOnClickListener {
            if(index == -1){
                Contacts().addContact(name.text.toString(), email.text.toString(), getSharedPreferences("pref", MODE_PRIVATE))
                name.text.clear()
                email.text.clear()
                Toast.makeText(this, "Контакт добавлен!", Toast.LENGTH_SHORT).show()
                finishActivity(0)
            } else {

                if (name.text.toString().trim() != "" && email.text.toString().trim() != ""){
                    contactList[index].name = name.text.toString()
                    contactList[index].email = email.text.toString()
                    Contacts().saveContacts(contactList, getSharedPreferences("pref", MODE_PRIVATE))
                    name.text.clear()
                    email.text.clear()
                    Toast.makeText(this, "Контакт изменен!", Toast.LENGTH_SHORT).show()
                    finishActivity(0)
                } else {
                    Toast.makeText(this, "Поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}
