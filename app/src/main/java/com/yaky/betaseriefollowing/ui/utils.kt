package com.yaky.betaseriefollowing.ui

import android.content.Context
import android.widget.Toast
import kotlin.reflect.KProperty

object DelegateExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}

class NotNullSingleValueVar<T>{

    private var value : T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T=
            value ?: throw IllegalStateException("${property.name} not initialized")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T){
        this.value = if(this.value == null){
                        value
                     } else{
                        throw IllegalStateException("${property.name} already init")
                    }
    }
}


fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()