package com.example.todo

import androidx.lifecycle.ViewModel

class MainViewModel(countReserved:Int):ViewModel() {
    var position=countReserved
}