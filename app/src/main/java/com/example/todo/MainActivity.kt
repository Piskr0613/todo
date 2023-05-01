package com.example.todo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
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

    lateinit var viewModel:MainViewModel

    lateinit var pref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        pref=getPreferences(Context.MODE_PRIVATE)
        val editor=pref.edit()
        val countReserved=pref.getInt("count_reserved",0)
        val data= mutableListOf<RvAdapter.Data>()
        viewModel=ViewModelProvider(this,MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        mBinding.button2.setOnClickListener {
            viewModel.position += 1
            val number= viewModel.position.toString()
            val new=mBinding.editText.text.toString()
            editor.putString(number,new)
            editor.apply()
            val thing=pref.getString(viewModel.position.toString(),"")
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

    override fun onPause() {
        super.onPause()
        pref.edit{putInt("count_reserved",viewModel.position)}
    }
}