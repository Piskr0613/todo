package com.example.todo

import android.os.Bundle
import android.widget.Toast
import com.example.todo.databinding.ActivityRegisterBinding

class RegisterActivity :SharedPreferencesActivity() {
    private val mBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.register.setOnClickListener {
            val account1=mBinding.username1.text.toString()
            val password1=mBinding.password1.text.toString()
            val password2=mBinding.password2.text.toString()
            if(register(account1,password1,password2)){
                Toast.makeText(this,"注册成功，请返回登录界面登录",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
