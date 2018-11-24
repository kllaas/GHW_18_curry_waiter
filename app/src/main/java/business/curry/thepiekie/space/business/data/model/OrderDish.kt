package business.curry.thepiekie.space.business.data.model

import android.os.Parcel
import android.os.Parcelable

data class OrderDish(
    private val id: String,
    private val userId: String,
    private val dishId: String,
    private val orderPlaceId: String,
    private val created: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(userId)
        parcel.writeString(dishId)
        parcel.writeString(orderPlaceId)
        parcel.writeLong(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderDish> {
        override fun createFromParcel(parcel: Parcel): OrderDish {
            return OrderDish(parcel)
        }

        override fun newArray(size: Int): Array<OrderDish?> {
            return arrayOfNulls(size)
        }
    }
}