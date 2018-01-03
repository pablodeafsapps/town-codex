package com.altran.towncodex.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

data class Inhabitant(internal val id: Int, internal val name: String,
                      private @SerializedName("thumbnail") val image: String,
                      private val age: Int, private val weight: Float, private val height: Float,
                      private @SerializedName("hair_color") val hairColor: String,
                      private val professions: List<String>,
                      private val friends: List<String>) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readString(),
            parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readString(),
            parcel.readArrayList(String::class.java.classLoader).filterIsInstance<String>(),
            parcel.readArrayList(String::class.java.classLoader).filterIsInstance<String>())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeInt(age)
        parcel.writeFloat(weight)
        parcel.writeFloat(height)
        parcel.writeString(hairColor)
        parcel.writeList(professions)
        parcel.writeList(friends)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Inhabitant> {
        override fun createFromParcel(parcel: Parcel): Inhabitant = Inhabitant(parcel)

        override fun newArray(size: Int): Array<Inhabitant?> = arrayOfNulls(size)
    }
}
