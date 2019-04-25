package com.newsapp.response

import android.os.Parcel
import android.os.Parcelable

data class ArticleResponseEntity(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val source: SourceResponseEntity?
) : Parcelable {
    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(author)
        parcel?.writeString(title)
        parcel?.writeString(description)
        parcel?.writeString(url)
        parcel?.writeString(urlToImage)
        parcel?.writeString(publishedAt)
        parcel?.writeString(content)
        parcel?.writeValue(source)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(SourceResponseEntity::class.java!!.classLoader) as SourceResponseEntity?
    )

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<ArticleResponseEntity> {
        override fun createFromParcel(parcel: Parcel): ArticleResponseEntity {
            return ArticleResponseEntity(parcel)
        }

        override fun newArray(size: Int): Array<ArticleResponseEntity?> {
            return arrayOfNulls(size)
        }
    }

}