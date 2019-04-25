package com.newsapp.response

import android.os.Parcel
import android.os.Parcelable


data class SourceResponseEntity(val id: String?,val name: String?) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(id)
        parcel?.writeString(name)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<SourceResponseEntity> {
        override fun createFromParcel(parcel: Parcel): SourceResponseEntity {
            return SourceResponseEntity(parcel)
        }

        override fun newArray(size: Int): Array<SourceResponseEntity?> {
            return arrayOfNulls(size)
        }
    }

}