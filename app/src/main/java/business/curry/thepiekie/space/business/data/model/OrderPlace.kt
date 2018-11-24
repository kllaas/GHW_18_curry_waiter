package business.curry.thepiekie.space.business.data.model

import com.google.gson.annotations.SerializedName

data class OrderPlace(
    private val id : String,
    private val userId : String,
    private val placeId : String,
    private val amountPersons : Int,
    private val status : String,
    private val bookingTime : Long,
    private val created : Long,
    private val updated : Long,
    private val orderDishes: List<OrderDish>
)