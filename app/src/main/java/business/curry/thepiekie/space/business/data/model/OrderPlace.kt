package business.curry.thepiekie.space.business.data.model

import java.text.SimpleDateFormat
import java.util.*

data class OrderPlace(
    private val id: String,
    private val userId: String,
    private val placeId: String,
    private val amountPersons: Int,
    private val status: String,
    private val bookingTime: Long,
    private val created: Long,
    private val updated: Long,
    private val orderDishes: List<OrderDish>
) {

    fun getBookingTimeFormatted(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(Date(bookingTime))
    }

    fun getAmountPersons() : Int = amountPersons
    fun getStatus() : String = status
    fun getOrderDishes() : List<OrderDish> = orderDishes
}