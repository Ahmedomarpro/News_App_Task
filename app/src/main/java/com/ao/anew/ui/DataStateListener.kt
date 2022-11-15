package com.ao.anew.ui

import com.ao.anew.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}