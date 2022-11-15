package com.ao.anew.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.ao.anew.model.Article

@Keep
data class NewsModel(
    @SerializedName("articles")
    var articles: List<Article>? = null,
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: Int = 0
)