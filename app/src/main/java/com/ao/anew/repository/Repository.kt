package com.ao.anew.repository

import androidx.lifecycle.LiveData
import com.ao.anew.model.NewsModel
import com.ao.anew.network.ApiService
import com.ao.anew.ui.state.MainViewState
import com.ao.anew.util.ApiSuccessResponse
import com.ao.anew.util.DataState
import com.ao.anew.util.GenericApiResponse
import javax.inject.Inject

class Repository @Inject constructor(val apiService: ApiService) {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<NewsModel, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<NewsModel>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        newsModel = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<NewsModel>> {
                return apiService.getNewsArtcles()
            }

        }.asLiveData()
    }
}




























