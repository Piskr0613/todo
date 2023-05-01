package com.example.todo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todo.databinding.ActivityLoginBinding
import com.example.todo.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity(){
    private val mBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val prefs=getPreferences(Context.MODE_PRIVATE)
        val isRemember=prefs.getBoolean("remember_password",false)
        if (isRemember){
            val account=prefs.getString("account","")
            val password=prefs.getString("password","")
            mBinding.username.setText(account)
            mBinding.password.setText(password)
            mBinding.rememberPass.isChecked=true
        }
        mBinding.login.setOnClickListener {
            val account=mBinding.username.text.toString()
            val password=mBinding.password.text.toString()
            val username=prefs.getString("accountIn","")
            val key=prefs.getString("passwordIn","")
            if(account==username&&password==key){
                val editor=prefs.edit()
                if(mBinding.rememberPass.isChecked){
                    editor.putBoolean("remember_password",true)
                    editor.putString("account",account)
                    editor.putString("password",password)
                }
                else{
                    editor.clear()
                }
                editor.apply()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
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