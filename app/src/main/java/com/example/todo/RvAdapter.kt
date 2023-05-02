package com.example.todo

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RvAdapter(private val data:List<Data>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    data class Data(val assignment:String)

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView=view.findViewById<TextView>(R.id.tv_assignment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.text,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData=data[position]
        holder.apply {
            textView.text=itemData.assignment
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}