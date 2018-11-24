package business.curry.thepiekie.space.business.data.model

data class Dish(
    private val id : String,
    private val name : String,
    private val placeId : String,
    private val price : Float,
    private val imageUrl : String,
    private val rating : Float,
    private val weight : Float,
    private val weightUnit : String
)