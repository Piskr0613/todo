package com.example.todo

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

    private val data= listOf(
        AdapterData("写作业"),
        AdapterData("吃药")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.rv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=RvAdapter(data)
            addItemDecoration(DividerItemDecoration(this@MainActivity,RecyclerView.VERTICAL))
        }
    }
}