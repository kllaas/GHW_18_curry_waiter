package business.curry.thepiekie.space.business.data.model

data class OrderDish(
    private val id : String,
    private val userId : String,
    private val dishId : String,
    private val orderPlaceId : String,
    private val created : Long
)