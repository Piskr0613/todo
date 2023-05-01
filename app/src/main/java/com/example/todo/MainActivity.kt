package com.example.todo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.todo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

typealias AdapterData=RvAdapter.Data
class MainActivity : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val pref=getPreferences(Context.MODE_PRIVATE)
        val editor=pref.edit()
        val data= mutableListOf<RvAdapter.Data>()
        var position=0
        mBinding.button2.setOnClickListener {
            position += 1
            val number= position.toString()
            val new=mBinding.editText.text.toString()
            editor.putString(number,new)
            editor.apply()
            val thing=pref.getString(position.toString(),"")
            data.add(AdapterData(thing.toString()))
        }
        mBinding.rv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=RvAdapter(data)
            addItemDecoration(DividerItemDecoration(this@MainActivity,RecyclerView.VERTICAL))
        }
        mBinding.button1.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}