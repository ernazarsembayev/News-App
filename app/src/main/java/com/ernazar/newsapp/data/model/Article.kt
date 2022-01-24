package com.ernazar.newsapp.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val source: Source?,
    val title: String?,
    val url: String?,
    val description: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    var isMarked: Boolean?,
) : Parcelable