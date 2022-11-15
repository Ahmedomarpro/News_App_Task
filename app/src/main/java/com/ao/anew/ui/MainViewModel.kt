package com.ao.anew.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ao.anew.model.NewsModel
import com.ao.anew.repository.Repository
import com.ao.anew.ui.state.MainStateEvent
import com.ao.anew.ui.state.MainViewState
import com.tushar.newsmvi.util.AbsentLiveData
import com.ao.anew.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState: LiveData<MainViewState>
        get() = _viewState

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){ stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        return when(stateEvent){
            is MainStateEvent.FetchNews ->{
                repository.getBlogPosts()
            }
            is MainStateEvent.None ->{
                AbsentLiveData.create()
            }

            
        }
    }

    fun setStateEvent(event: MainStateEvent){
        _stateEvent.value = event
    }

    fun setNewsListData(news: NewsModel?){
        val update = getCurrentViewStateOrNew()
        update.newsModel = news
        _viewState.value = update
    }

    private fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value ?: MainViewState()
    }
}