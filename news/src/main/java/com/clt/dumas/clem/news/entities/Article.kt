package com.clt.dumas.clem.news.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    @PrimaryKey var title: String, var description: String,
    var url: String,
    var author: String = "Anonymous",
    var urlToImage: String = "https://www.omv.fr/uploads/files/image-t-site1route-8.jpg",
    var isLiked: Boolean = false
) :
    Parcelable