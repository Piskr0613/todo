package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.todo.databinding.ActivityLoginBinding


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
            //检验密码是否正确
            if(login(account,password)){
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show()
            }
        }
        //注册界面跳转
        mBinding.register.setOnClickListener {
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
