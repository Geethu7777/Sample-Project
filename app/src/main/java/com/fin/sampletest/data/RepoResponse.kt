package com.fin.sampletest.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "reporesponse")
data class RepoResponse (
    @PrimaryKey
    var id                       : Int?              = null,
    @ColumnInfo(name ="name")
    var name                 : String?           = null,
    @ColumnInfo (name ="owner")
     var owner                    : Owner?            = Owner(),
    @ColumnInfo (name ="description")
     var description              : String?           = null,
    @ColumnInfo (name ="language")
     var language                 : String?           = null


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        TODO("owner"),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(language)
    }

    companion object CREATOR : Parcelable.Creator<RepoResponse> {
        override fun createFromParcel(parcel: Parcel): RepoResponse {
            return RepoResponse(parcel)
        }

        override fun newArray(size: Int): Array<RepoResponse?> {
            return arrayOfNulls(size)
        }
    }
}