package com.tapi.viewmodellesson

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MyViewModel() : ViewModel() {
    private val TAG: String = "HELLO"

    private val filter = MutableLiveData<String>()
    private val data = MutableLiveData<List<String>>()
    private val _listRS = MediatorLiveData<List<String>?>()


/*
    private val result: LiveData<List<String>> = Transformations.map(filter) { textFiler ->
        data.value?.let {
            runFilter(data.value!!, textFiler)
        }
    }
*/

    private val result1: LiveData<List<String>> = Transformations.switchMap(data) { _data ->
        Transformations.map(filter) {
            runFilter(_data, it)
        }
    }

    val rs: LiveData<List<String>> = TranfomationsUnit.map(data, filter) { d, textFilter ->
        if (d != null && textFilter != null) {
            runFilter(d, textFilter)
        } else {
            emptyList()
        }
    }

    init {
        viewModelScope.launch {
            while (true) {
                val array = mutableListOf<String>()
                for (i in 0 until 20) {
                    val random =
                        randomChar() + randomChar() + randomChar() + randomChar() + randomChar()
                    array.add(random)
                }
                data.value = array
                Log.d("check", "data = ${data.value}")
                delay(5000)
            }
        }

        _listRS.addSource(data) {
            _listRS.postValue(data.value)
        }

        _listRS.addSource(filter) { textFilter ->
            val listRs: List<String>
            data.value?.let {
                listRs = runFilter(data.value!!, textFilter)
                _listRS.postValue(listRs)
            }


        }

    }


    fun randomChar(): String {
        return Random.nextInt('a'.toInt(), 'h'.toInt()).toChar().toString()
    }


    fun setFilter(filter: String) {
        this.filter.value = filter
    }

    fun runFilter(src: List<String>, filter: String): List<String> {
        return src.filter {
            it.startsWith(filter)
        }
    }


    fun getResultData(): LiveData<List<String>> {
        return result1
    }

}