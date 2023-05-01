package com.example.todo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todo.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val mBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val pref=getPreferences(Context.MODE_PRIVATE)
        val editor=pref.edit()
        mBinding.register.setOnClickListener {
            val account1=mBinding.username1.text.toString()
            val password1=mBinding.password1.text.toString()
            val password2=mBinding.password2.text.toString()
            if (password1==password2){
                editor.putString("passwordIn",password1)
                editor.putString("accountIn",account1)
                Toast.makeText(this,"注册成功，请返回登录界面登录",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"两次密码不一致，请确认密码",Toast.LENGTH_SHORT).show()
            }
                editor.apply()
        }

    }
}