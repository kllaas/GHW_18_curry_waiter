package business.curry.thepiekie.space.business.data.model

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class OrderPlace(
    val id: String,
    val userId: String,
    val placeId: String,
    val amountPersons: Int,
    val status: String,
    val bookingTime: Long,
    val created: Long,
    val updated: Long,
    val orderDishes: List<Dish>
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.createTypedArrayList(Dish)
    )

    fun getBookingTimeFormatted(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(Date(bookingTime))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(userId)
        parcel.writeString(placeId)
        parcel.writeInt(amountPersons)
        parcel.writeString(status)
        parcel.writeLong(bookingTime)
        parcel.writeLong(created)
        parcel.writeLong(updated)
        parcel.writeTypedList(orderDishes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderPlace> {
        override fun createFromParcel(parcel: Parcel): OrderPlace {
            return OrderPlace(parcel)
        }

        override fun newArray(size: Int): Array<OrderPlace?> {
            return arrayOfNulls(size)
        }
    }

}