package com.ao.anew.ui.state

sealed class MainStateEvent {

    object FetchNews : MainStateEvent()

    object None: MainStateEvent()
}