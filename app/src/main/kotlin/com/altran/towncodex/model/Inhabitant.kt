package com.altran.towncodex.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//
//data class Inhabitant(@PrimaryKey internal var id: Int = 0, internal var name: String? = null,
//                      internal @SerializedName("thumbnail") var image: String? = null,
//                      internal var age: Int = 0, internal var weight: Float = 0f,
//                      internal var height: Float = 0f,
//                      internal @SerializedName("hair_color") var hairColor: String? = null,
//                      internal var professions: List<String>? = null,
//                      internal var friends: List<String>? = null) : RealmObject(), Parcelable {
open class Inhabitant() : RealmObject(), Parcelable {

    @PrimaryKey
    internal var id: Int = 0
    internal var name: String? = null
    internal @SerializedName("thumbnail") var image: String? = null
    internal var age: Int = 0
    internal var weight: Float = 0f
    internal var height: Float = 0f
    internal @SerializedName("hair_color") var hairColor: String? = null
    internal var professions: RealmList<String>? = null
    internal var friends: RealmList<String>? = null

    constructor(parcel: Parcel) : this() {
        this.id = parcel.readInt()
        this.name = parcel.readString()
        this.image = parcel.readString()
        this.age = parcel.readInt()
        this.weight = parcel.readFloat()
        this.height = parcel.readFloat()
        this.hairColor = parcel.readString()
        this.professions = parcel.readArrayList(String::class.java.classLoader).filterIsInstance<String>() as RealmList<String>
        this.friends = parcel.readArrayList(String::class.java.classLoader).filterIsInstance<String>() as RealmList<String>
    }

//    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readString(),
//            parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readString(),
//            parcel.readArrayList(String::class.java.classLoader).filterIsInstance<String>(),
//            parcel.readArrayList(String::class.java.classLoader).filterIsInstance<String>())

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
