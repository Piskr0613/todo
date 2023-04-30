package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.Frag1Binding

class Fragment2 : Fragment() {
    private val mBinding by lazy { Frag1Binding.inflate(layoutInflater) }
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

}