package com.tapi.viewmodellesson

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewmodelFactory(
    val pos: Int,
    val title: String,
    val description: String,
    val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel() as T
        /*    return modelClass.getConstructor(
                Int::class.java,
                String::class.java,
                String::class.java,
                Application::class.java
            ).newInstance(pos, title, description, application)*/
        }
        throw Exception("Exception")
    }
}