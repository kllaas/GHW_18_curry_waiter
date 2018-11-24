package business.curry.thepiekie.space.business.data.model

import android.os.Parcel
import android.os.Parcelable

data class Dish(
    val id: String,
    val name: String,
    val placeId: String,
    val price: Float,
    val imageUrl: String,
    val rating: Float,
    val weight: Float,
    val weightUnit: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(placeId)
        parcel.writeFloat(price)
        parcel.writeString(imageUrl)
        parcel.writeFloat(rating)
        parcel.writeFloat(weight)
        parcel.writeString(weightUnit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dish> {
        override fun createFromParcel(parcel: Parcel): Dish {
            return Dish(parcel)
        }

        override fun newArray(size: Int): Array<Dish?> {
            return arrayOfNulls(size)
        }
    }
}