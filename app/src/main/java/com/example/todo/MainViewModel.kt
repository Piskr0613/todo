package com.example.todo

import androidx.lifecycle.ViewModel

class MainViewModel(dataReserved:List<RvAdapter.Data>):ViewModel() {
    var data= dataReserved
}