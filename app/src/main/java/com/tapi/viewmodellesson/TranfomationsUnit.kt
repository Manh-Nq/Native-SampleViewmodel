package com.tapi.viewmodellesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class TranfomationsUnit {
    companion object {
        fun <X, Y, Z> map(
            sourceX: LiveData<X>,
            sourceY: LiveData<Y>,
            mapFunction: (x: X?, y: Y?) -> Z
        ): LiveData<Z> {
            val result = MediatorLiveData<Z>()
            result.addSource(sourceX) { x ->
                result.value = mapFunction(x, sourceY.value)
            }
            result.addSource(sourceY) { y ->
                result.value = mapFunction(sourceX.value, y)
            }
            return result
        }

        fun <X, Y, Z, D> mapSource(
            sourceX: LiveData<X>,
            sourceY: LiveData<Y>,
            sourceD: LiveData<D>,
            mapFunction: (x: X?, y: Y?, d: D?) -> Z
        ): LiveData<Z> {
            val result = MediatorLiveData<Z>()
            result.addSource(sourceX) { x ->
                result.value = mapFunction(x, sourceY.value, sourceD.value)
            }
            result.addSource(sourceY) { y ->
                result.value = mapFunction(sourceX.value, y, sourceD.value)
            }
            result.addSource(sourceD) { d ->
                result.value = mapFunction(sourceX.value, sourceY.value, d)
            }
            return result
        }
    }
}