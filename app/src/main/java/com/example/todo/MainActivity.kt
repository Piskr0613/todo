package com.example.todo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ActivityMainBinding
import java.util.*

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
        val dataReserved=mutableListOf<RvAdapter.Data>()
        var position=0
        viewModel=ViewModelProvider(this,MainViewModelFactory(dataReserved)).get(MainViewModel::class.java)
        mBinding.button2.setOnClickListener {
            position += 1
            val number= position.toString()
            val new=mBinding.editText.text.toString()
            editor.putString(number,new)
            editor.apply()
            val thing=pref.getString(position.toString(),"")
            dataReserved.add(AdapterData(thing.toString()))
            refresh()
        }

        refresh()

        mBinding.button1.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                // 允许往上往下拖拽移动
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                // 允许左滑右滑删除
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // 开启长按拖拽，并且拖拽改变顺序
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                // 交换下位置
                Collections.swap(dataReserved, from, to)
                // notify 下
                mBinding.rv.adapter!!.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 被滑走时 这里把 item 删掉
                val pos = viewHolder.adapterPosition
                dataReserved.removeAt(pos)
                mBinding.rv.adapter!!.notifyItemRemoved(pos)
            }

        })
        helper.attachToRecyclerView(mBinding.rv)
    }

    private fun refresh(){
        mBinding.rv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=RvAdapter(viewModel.data)
            addItemDecoration(DividerItemDecoration(this@MainActivity,RecyclerView.VERTICAL))
        }
    }
}