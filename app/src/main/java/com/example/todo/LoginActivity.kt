package com.example.todo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todo.databinding.ActivityLoginBinding
import com.example.todo.databinding.ActivityMainBinding
import android.content.SharedPreferences

class LoginActivity :SharedPreferencesActivity(){
    private val mBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.login.setOnClickListener {
            val account=mBinding.username.text.toString()
            val password=mBinding.password.text.toString()
            if(login(account,password)){
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.register.setOnClickListener {
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}