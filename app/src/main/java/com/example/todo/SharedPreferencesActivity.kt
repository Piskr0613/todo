package com.example.todo

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class SharedPreferencesActivity:AppCompatActivity() {
    fun register(username:String,key1:String,key2:String):Boolean{
        val pref=getSharedPreferences("data",Context.MODE_PRIVATE)
        val account=username
        val password1=key1
        val password2=key2
        if (password1==password2){
            val editor=pref.edit()
            editor.putString("password",password1)
            editor.putString("account",account)
            editor.apply()
            return true
        }else{
            return false
        }

    }

     fun login(account:String,password:String):Boolean{
         val pref=getSharedPreferences("data",Context.MODE_PRIVATE)
         val user=pref.getString("account","")
         val pass=pref.getString("password","")
         return account==user&&password==pass
    }
}